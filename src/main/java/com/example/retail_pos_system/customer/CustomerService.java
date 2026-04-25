package com.example.retail_pos_system.customer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.retail_pos_system.billing.Billing;
import com.example.retail_pos_system.billing.BillingDTO;
import com.example.retail_pos_system.billing.BillingItemsDTO;
import com.example.retail_pos_system.billing.BillingRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;
	private BillingRepository billingRepository;

	public CustomerService(CustomerRepository customerRepository, BillingRepository billingRepository) {
		this.customerRepository = customerRepository;
		this.billingRepository = billingRepository;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getByPhone(String phone) {
		return customerRepository.findByPhone(phone).orElseThrow(() -> new RuntimeException("Customer Not Found"));
	}
	
	public List<BillingDTO> getCustomerBills(Long customerId) {

	    List<Billing> bills = billingRepository.findBillsWithItemByCustomerId(customerId);

	    return bills.stream().map(bill -> {

	        BillingDTO dto = new BillingDTO();
	        dto.setId(bill.getId());
	        dto.setCreatedAt(bill.getCreatedAt().toString());
	        dto.setGrandTotal(bill.getGrandTotal());

	        List<BillingItemsDTO> items = bill.getItems().stream().map(item -> {
	            BillingItemsDTO itemDTO = new BillingItemsDTO();
	            itemDTO.setProductName(item.getProduct().getName());
	            itemDTO.setQuantity(item.getQuantity());
	            itemDTO.setPrice(item.getPrice());
	            itemDTO.setTotal(item.getTotal());
	            return itemDTO;
	        }).toList();

	        dto.setItems(items);

	        return dto;

	    }).toList();
	}
}
