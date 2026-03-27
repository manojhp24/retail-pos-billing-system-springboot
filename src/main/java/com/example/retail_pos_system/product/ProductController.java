package com.example.retail_pos_system.product;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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