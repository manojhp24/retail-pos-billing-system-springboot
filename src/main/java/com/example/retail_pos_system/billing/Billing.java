package com.example.retail_pos_system.billing;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "billing")
public class Billing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double totalAmount;

	private double gstAmount;

	private double discount;

	private double grandTotal;
	
	private String customerName;
	private String customerPhone;

	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<BillItem> items;

	public Long getId() {
		return id;

	}

	public double getTotalAmount() {
		return totalAmount;

	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(double gstAmount) {
		this.gstAmount = gstAmount;
	}

	public double getDiscount() {
		return discount;

	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;

	}
	
	public List<BillItem> getItems() {
		return items;
	}

	public void setItems(List<BillItem> items) {
		this.items = items;

	}
	
	public String getCustomerName() {
	    return customerName;
	}

	public void setCustomerName(String customerName) {
	    this.customerName = customerName;
	}

	public String getCustomerPhone() {
	    return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
	    this.customerPhone = customerPhone;
	}


}
