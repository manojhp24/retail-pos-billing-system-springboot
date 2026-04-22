package com.example.retail_pos_system.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findBySku(String sku);

	List<Product> findByActiveTrue();

}
