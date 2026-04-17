package com.example.retail_pos_system.billing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.retail_pos_system.inventory.Inventory;
import com.example.retail_pos_system.inventory.InventoryRepository;
import com.example.retail_pos_system.product.Product;
import com.example.retail_pos_system.product.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BillingService {

	private ProductService productService;
	private InventoryRepository inventoryRepository;
	private BillingRepository billingRepository;

	public BillingService(ProductService productService, InventoryRepository inventoryRepository,
			BillingRepository billingRepository) {
		this.productService = productService;
		this.inventoryRepository = inventoryRepository;
		this.billingRepository = billingRepository;
	}

	public Billing createBill(BillingRequest req) {
		List<BillItem> items = new ArrayList<>();

		double totalAmount = 0;
		
		for (BillingRequest.Item i : req.items) {
			Long productId = i.productId;
			int quantity = i.quantity;

			Product product = productService.getById(productId);
			Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);

			Inventory inv = inventory.orElseThrow(() -> new RuntimeException("Inventory not found"));

			int currentStock = inv.getStock();

			if (currentStock < quantity) {
				throw new RuntimeException("Not Enough stock");
			}

			inv.setStock(currentStock - quantity);
			inventoryRepository.save(inv);

			double itemTotal = product.getPrice() * quantity;
			BillItem bi = new BillItem();

			bi.setProduct(product);
			bi.setQuantity(quantity);
			bi.setPrice(product.getPrice());
			bi.setTotal(itemTotal);

			items.add(bi);
			totalAmount+=itemTotal;
		}
		
		double gstAmount = totalAmount * 18 / 100;
		double grandTotal = gstAmount + totalAmount - req.discount;

		Billing bill = new Billing();
		bill.setTotalAmount(totalAmount);
		bill.setGstAmount(gstAmount);
		bill.setGrandTotal(grandTotal);
		bill.setDiscount(req.discount);
		bill.setCreatedAt(LocalDateTime.now());
		
		for(BillItem bi:items) {
			bi.setBill(bill);
		}
		bill.setItems(items);
		
		
		
		return billingRepository.save(bill);
	}
	
	public Billing getBillById(Long id) {
	    return billingRepository.findBillWithItems(id);
	}
}
