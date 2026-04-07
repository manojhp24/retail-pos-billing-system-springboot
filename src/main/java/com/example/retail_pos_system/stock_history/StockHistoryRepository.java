
package com.example.retail_pos_system.stock_history;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author manoj
 */
public interface  StockHistoryRepository extends JpaRepository<StockHistory, Long> {
    List<StockHistory> findByProductId(Long productId);
    void deleteByProductId(Long id);
}
