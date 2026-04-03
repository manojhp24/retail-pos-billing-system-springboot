package com.example.retail_pos_system.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.retail_pos_system.inventory.InventoryRepository;
import com.example.retail_pos_system.inventory.InventoryService;

import jakarta.transaction.Transactional;
/**
 * It Handles business logic for product operations & CRUD - operations
 */

@Service
public class ProductService {
	private final ProductRepository repository;
	private final InventoryService inventoryService;
	private final InventoryRepository inventoryRepository;

	public ProductService(ProductRepository repository, InventoryService inventoryService,InventoryRepository inventoryRepository) {
		this.repository = repository;
		this.inventoryService = inventoryService;
		this.inventoryRepository = inventoryRepository;
	}

	// Save or update the product in database
	public Product save(Product product) {
		Product savedProduct = repository.save(product);

		 inventoryService.createInventory(savedProduct.getId());

		 return savedProduct;
	}


	// Fetch all the products data form database
	public List<Product> getAll() {
		return repository.findAll();

	}

	// Fetch product data by its id
	public Product getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Product update(Long id, Product updatedProduct) {
		Product productExist = repository.findById(id).orElse(null);

		if (productExist == null) {
			return null;
		}

		productExist.setName(updatedProduct.getName());
		productExist.setPrice(updatedProduct.getPrice());
	
		productExist.setCategory(updatedProduct.getCategory());

		return repository.save(productExist);
	}

	// Delete the product data by id
	@Transactional
	public void delete(Long id) {
		inventoryRepository.deleteByProductId(id);
		repository.deleteById(id);

	}
}
