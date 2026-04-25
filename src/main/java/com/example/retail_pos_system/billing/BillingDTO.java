package com.example.retail_pos_system.billing;

import java.util.List;

public class BillingDTO {

	private Long id;
	private String createdAt;
	private double grandTotal;
	private List<BillingItemsDTO> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public List<BillingItemsDTO> getItems() {
		return items;
	}

	public void setItems(List<BillingItemsDTO> items) {
		this.items = items;
	}
}