package com.example.retail_pos_system.inventory;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.retail_pos_system.product.Product;
import com.example.retail_pos_system.product.ProductRepository;

@Service
public class InventoryService {
	private final ProductRepository productRepository;

	private final InventoryRepository inventoryRepository;

	public InventoryService(ProductRepository productRepository, InventoryRepository inventoryRepository) {
		this.productRepository = productRepository;
		this.inventoryRepository = inventoryRepository;

	}

	public void createInventory(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product Not Found"));

		Inventory inventory = new Inventory();

		inventory.setProduct(product);
		inventory.setStock(0);
		inventory.setLastUpdated(LocalDateTime.now());
	}

	public List<Inventory> getAllInventory() {
		return inventoryRepository.findAll();
	}

	public Inventory restock(Long productId, int quantity) {
		Inventory inventory = inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Inventory not found"));

		inventory.setStock(inventory.getStock() + quantity);
		inventory.setLastUpdated(LocalDateTime.now());

		return inventoryRepository.save(inventory);

	}

	public Inventory reduceStock(Long productId, int quantity) {
		Inventory inventory = inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		inventory.setStock(inventory.getStock() - quantity);
		inventory.setLastUpdated(LocalDateTime.now());

		return inventoryRepository.save(inventory);
	}

}
