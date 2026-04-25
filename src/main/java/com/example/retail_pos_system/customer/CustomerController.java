package com.example.retail_pos_system.customer;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.retail_pos_system.billing.BillingDTO;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public List<Customer> fetchAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	
	 @GetMapping("/{customerId}/bills")
	public List<BillingDTO> fethCustomerBills(@PathVariable Long customerId){
		return customerService.getCustomerBills(customerId);
	}
}
