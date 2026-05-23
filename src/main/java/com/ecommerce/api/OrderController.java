package com.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // PLACE ORDER (Checkout)
    @PostMapping("/checkout/{userId}")
    public Order placeOrder(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);

        return orderService.placeOrder(user, cartItems);
    }

    // GET USER ORDERS
    @GetMapping("/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderService.getOrdersByUser(user);
    }
}