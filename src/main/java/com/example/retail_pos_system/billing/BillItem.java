package com.example.retail_pos_system.billing;

import jakarta.persistence.*;
import com.example.retail_pos_system.product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "bill_items")
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double price;
    private double total;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bill_id", nullable = false)
    @JsonBackReference
    private Billing bill;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // total
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // billing
    public Billing getBill() {
        return bill;
    }

    public void setBill(Billing bill) {
        this.bill = bill;
    }

    // product
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}