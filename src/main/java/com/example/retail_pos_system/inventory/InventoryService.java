package com.example.retail_pos_system.inventory;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.retail_pos_system.product.Product;
import com.example.retail_pos_system.product.ProductRepository;
import com.example.retail_pos_system.stock_history.StockHistoryService;

@Service
public class InventoryService {
	private final ProductRepository productRepository;

	private final InventoryRepository inventoryRepository;
	
	private final StockHistoryService stockHistoryService;
	
	public InventoryService(ProductRepository productRepository, InventoryRepository inventoryRepository,StockHistoryService stockHistoryService) {
		this.productRepository = productRepository;
		this.inventoryRepository = inventoryRepository;
		this.stockHistoryService = stockHistoryService;

	}

	public void createInventory(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product Not Found"));

		Inventory inventory = new Inventory();

		inventory.setProduct(product);
		inventory.setStock(0);
		inventory.setLastUpdated(LocalDateTime.now());
		inventoryRepository.save(inventory);
	}

	public List<Inventory> getAllInventory() {
		return inventoryRepository.findByProductActiveTrue();
	}

	public Inventory restock(Long productId, int quantity) {
		Inventory inventory = inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Inventory not found"));

		inventory.setStock(inventory.getStock() + quantity);
		inventory.setLastUpdated(LocalDateTime.now());
			
		stockHistoryService.saveHistory(productId, "RESTOCK", quantity);
		
		return inventoryRepository.save(inventory);

	}

	public Inventory reduceStock(Long productId, int quantity) {
		Inventory inventory = inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		inventory.setStock(inventory.getStock() - quantity);
		inventory.setLastUpdated(LocalDateTime.now());
		
		stockHistoryService.saveHistory(productId, "REDUCE", quantity);

		return inventoryRepository.save(inventory);
	}

}
