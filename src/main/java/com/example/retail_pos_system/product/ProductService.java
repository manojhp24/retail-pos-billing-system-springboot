package com.example.retail_pos_system.product;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * It Handles business logic for product operations & CRUD - operations
 */

@Service
public class ProductService {
	private final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	// Save or update the product in database
	public Product save(Product product) {
		return repository.save(product);
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
		productExist.setQuantity(updatedProduct.getQuantity());

		return repository.save(productExist);
	}

	// Delete the product data by id
	public void delete(Long id) {
		repository.deleteById(id);

	}
}
