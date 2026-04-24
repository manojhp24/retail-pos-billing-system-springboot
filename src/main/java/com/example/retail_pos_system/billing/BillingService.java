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
	    double totalTax = 0;
	    
	    if(req.customerName == null || req.customerName.isBlank()) {
	    	throw new RuntimeException("Customer name required");
	    }

	    for (BillingRequest.Item i : req.items) {

	        Long productId = i.productId;
	        int quantity = i.quantity;

	        Product product = productService.getById(productId);

	        Inventory inv = inventoryRepository.findByProductId(productId)
	                .orElseThrow(() -> new RuntimeException("Inventory not found"));

	        if (inv.getStock() < quantity) {
	            throw new RuntimeException("Not enough stock");
	        }

	        // Reduce stock
	        inv.setStock(inv.getStock() - quantity);
	        inventoryRepository.save(inv);

	        double price = product.getSellingPrice();
	        double itemTotal = price * quantity;

	        // GST per product
	        double tax = itemTotal * product.getTaxPercent() / 100;

	        BillItem bi = new BillItem();
	        bi.setProduct(product);
	        bi.setQuantity(quantity);
	        bi.setPrice(price);
	        bi.setTotal(itemTotal);
	      

	        items.add(bi);

	        totalAmount += itemTotal;
	        totalTax += tax;
	    }

	    double grandTotal = totalAmount + totalTax - req.discount;

	    Billing bill = new Billing();
	    bill.setCustomerName(req.customerName);
	    bill.setCustomerPhone(req.customerPhone);
	    bill.setTotalAmount(totalAmount);
	    bill.setGstAmount(totalTax);
	    bill.setGrandTotal(grandTotal);
	    bill.setDiscount(req.discount);
	    bill.setCreatedAt(LocalDateTime.now());

	    for (BillItem bi : items) {
	        bi.setBill(bill);
	    }

	    bill.setItems(items);

	    return billingRepository.save(bill);
	}
	public Billing getBillById(Long id) {
	    return billingRepository.findBillWithItems(id);
	}
}
