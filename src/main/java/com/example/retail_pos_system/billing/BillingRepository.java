package com.example.retail_pos_system.billing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BillingRepository extends JpaRepository<Billing, Long> {
	@Query("SELECT b FROM Billing b LEFT JOIN FETCH b.items WHERE b.id = :id")
	Billing findBillWithItems(@Param("id") Long id);
	List<Billing> findBillsWithItemByCustomerId(Long customerId);

}
