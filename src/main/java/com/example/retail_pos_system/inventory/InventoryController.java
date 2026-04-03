package com.example.retail_pos_system.inventory;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	private final InventoryService inventoryService;

	public InventoryController(InventoryService service) {
		this.inventoryService = service;
	}

	@GetMapping
	public List<Inventory> getAllInventory() {
		return inventoryService.getAllInventory();
	}

	@PutMapping("/restock/{productId}")
	public Inventory restock(@PathVariable Long productId, @RequestParam int quantity) {
		return inventoryService.restock(productId, quantity);

	}

	@PutMapping("/reduce/{productId}")
	public Inventory reduceStock(@PathVariable Long productId, @RequestParam int quantity) {
		return inventoryService.reduceStock(productId, quantity);
	}

}