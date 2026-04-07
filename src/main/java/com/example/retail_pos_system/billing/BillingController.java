package com.example.retail_pos_system.billing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBill(@RequestBody BillingRequest req) {
        billingService.createBill(req);
        return ResponseEntity.ok("Bill created successfully");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Billing> getBill(@PathVariable Long id) {
        return ResponseEntity.ok(billingService.getBillById(id));
    }
}