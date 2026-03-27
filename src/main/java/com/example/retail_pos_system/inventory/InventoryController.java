package com.example.retail_pos_system.inventory;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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

	@PutMapping("/{productId}")
	public Inventory restock(@PathVariable Long productId, @RequestParam int quantity) {
		return inventoryService.restock(productId, quantity);

	}

	@PutMapping("/reduce/{productId}")
	public Inventory reduceStock(@PathVariable Long productId, @RequestParam int quantity) {
		return inventoryService.reduceStock(productId, quantity);
	}

}