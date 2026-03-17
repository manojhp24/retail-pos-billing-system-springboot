package com.example.retail_pos_system.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/home")
    public String home() {
        return "Retail POS System is Running 🚀";
    }
}