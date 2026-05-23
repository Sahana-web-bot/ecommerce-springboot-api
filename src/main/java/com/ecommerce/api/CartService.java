package com.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CartItem addItemToCart(Long userId, Long productId, int quantity) {
        // 1. Verify product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 2. Business Rule: Check catalog availability before adding to cart
        if (product.getQuantity() < quantity) {
            throw new InsufficientStockException("Cannot add to cart. Only " + product.getQuantity() + " units available.");
        }

        // 3. Create and persist the cart item record
        CartItem cartItem = new CartItem();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getUserCart(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }
    
    public void removeCartItem(Long cartItemId) {

        if (!cartItemRepository.existsById(cartItemId)) {
            throw new RuntimeException("Cart item not found");
        }

        cartItemRepository.deleteById(cartItemId);
    }
}