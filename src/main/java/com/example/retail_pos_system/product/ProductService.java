package com.example.retail_pos_system.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.retail_pos_system.inventory.InventoryService;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final InventoryService inventoryService;

    public ProductService(ProductRepository repository, InventoryService inventoryService) {
        this.repository = repository;
        this.inventoryService = inventoryService;
    }

    // Create or reactivate product
    public Product save(Product product) {

        Product existing = repository.findBySku(product.getSku());

        if (existing != null) {
            existing.setActive(true);
            existing.setName(product.getName());
            existing.setCategory(product.getCategory());
            existing.setBrand(product.getBrand());
            existing.setCostPrice(product.getCostPrice());
            existing.setSellingPrice(product.getSellingPrice());
            existing.setTaxPercent(product.getTaxPercent());
            existing.setUnit(product.getUnit());
            existing.setUnitValue(product.getUnitValue());
            existing.setBaseUnit(product.getBaseUnit());

            existing.setDescription(product.getDescription());
            existing.setBarcode(product.getBarcode());

            return repository.save(existing);
        }

        Product savedProduct = repository.save(product);

        // create inventory entry
        inventoryService.createInventory(savedProduct.getId());

        return savedProduct;
    }

    // Get all active products
    public List<Product> getAll() {
        return repository.findByActiveTrue();
    }

    // Get by id
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Update product
    public Product update(Long id, Product updatedProduct) {

        Product productExist = getById(id);

        productExist.setName(updatedProduct.getName());
        productExist.setCategory(updatedProduct.getCategory());
        productExist.setBrand(updatedProduct.getBrand());
        productExist.setCostPrice(updatedProduct.getCostPrice());
        productExist.setSellingPrice(updatedProduct.getSellingPrice());
        productExist.setTaxPercent(updatedProduct.getTaxPercent());
        productExist.setUnit(updatedProduct.getUnit());
        productExist.setUnitValue(updatedProduct.getUnitValue());
        productExist.setBaseUnit(updatedProduct.getBaseUnit());

        productExist.setDescription(updatedProduct.getDescription());
        productExist.setBarcode(updatedProduct.getBarcode());

        return repository.save(productExist);
    }

    // Soft delete
    @Transactional
    public void delete(Long id) {
        Product product = getById(id);
        product.setActive(false);
        repository.save(product);
    }
}