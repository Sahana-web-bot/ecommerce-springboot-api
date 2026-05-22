package com.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Handles checkout requests via HTTP POST
    @PostMapping("/checkout")
    public Order checkout(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        
        return orderService.processCheckout(userId, productId, quantity);
    }
}