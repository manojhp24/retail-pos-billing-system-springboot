package com.example.retail_pos_system.product;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@PostMapping
	public Product create(@RequestBody Product product) {
		return service.save(product);
	}

	@GetMapping
	public List<Product> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public Product getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PutMapping("/{id}")
	public Product update(@PathVariable Long id, @RequestBody Product updatedProduct) {
		return service.update(id, updatedProduct);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}