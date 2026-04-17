package com.example.retail_pos_system.product;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity represents the Product table in the POS System
 */
@Entity
@Table(name = "products")
public class Product {
	@Id
	// It used to auto-generate ID using database
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private double price;
	private String category;
	private boolean active = true;
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	// It used set created time automatically before insert
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	
	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void  setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
	public boolean isActive() {
	    return active;
	}

	public void setActive(boolean active) {
	    this.active = active;
	}

}
