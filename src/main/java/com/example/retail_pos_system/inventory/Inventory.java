package com.example.retail_pos_system.inventory;

import java.time.LocalDateTime;

import com.example.retail_pos_system.product.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "product_id", unique = true)
	private Product product;

	private Integer stock;

	@Column(name = "lastUpdated")
	private LocalDateTime lastUpdated;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;

	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastupdated) {
		this.lastUpdated = lastupdated;
	}


}
