package com.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order processCheckout(Long userId, Long productId, int quantityToBuy) {

        // 1. Validate User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Validate Product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 3. Check Stock
        if (product.getQuantity() < quantityToBuy) {
            throw new InsufficientStockException("Not enough stock available");
        }

        // 4. Deduct Stock
        product.setQuantity(product.getQuantity() - quantityToBuy);
        productRepository.save(product);

        // 5. Create Order
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("COMPLETED");

        double totalCost = product.getPrice() * quantityToBuy;
        order.setTotalAmount(totalCost);

        // 6. Create OrderItem
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantityToBuy);
        item.setPrice(product.getPrice());

        // 7. Attach OrderItem to Order
        order.setItems(java.util.List.of(item));

        // 8. Save Order (cascades OrderItem)
        return orderRepository.save(order);
    }
}