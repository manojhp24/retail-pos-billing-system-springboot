package com.example.retail_pos_system.stock_history;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory/history")
public class StockHistoryController {
	
	private final StockHistoryService stockHistoryService;
	
	public StockHistoryController(StockHistoryService stockHistoryService) {
		this.stockHistoryService = stockHistoryService;
	}
	
	@GetMapping("/{productId}")
	public List<StockHistory> getHistory(@PathVariable Long productId){
		return stockHistoryService.getHistoryByProduct(productId);
	}

}
