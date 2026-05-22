package com.ecommerce.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Custom query method to find all cart items for a specific user
    List<CartItem> findByUserId(Long userId);
    
    // Custom query method to clear out a user's cart after a successful checkout
    void deleteByUserId(Long userId);
}