
package com.example.retail_pos_system.stock_history;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.retail_pos_system.product.Product;
import com.example.retail_pos_system.product.ProductRepository;

@Service
public class StockHistoryService {
	private final StockHistoryRepository stockHistoryRepository;

	private final ProductRepository productRepository;

	public StockHistoryService(StockHistoryRepository stockHistoryRepository, ProductRepository productRepository) {
		this.stockHistoryRepository = stockHistoryRepository;
		this.productRepository = productRepository;
	}

	public void saveHistory(Long productId, String action, int quantity) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		
		StockHistory history = new StockHistory();
		history.setProduct(product);
		history.setAction(action);
		history.setQuantity(quantity);
		history.setCreatedAt(LocalDateTime.now());
		
		stockHistoryRepository.save(history);

	}
	
	public List<StockHistory> getHistoryByProduct(Long productId){
		return stockHistoryRepository.findByProductId(productId);
	}

}
