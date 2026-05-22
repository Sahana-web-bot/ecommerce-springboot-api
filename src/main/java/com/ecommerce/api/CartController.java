package com.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;

    // ADD TO CART
    @PostMapping("/{userId}/add")
    public CartResponseDTO addToCart(@PathVariable Long userId,
                                     @RequestBody CartRequest request) {

        CartItem item = cartService.addItemToCart(
                userId,
                request.getProductId(),
                request.getQuantity()
        );

        return toDTO(item);
    }

    // GET CART
    @GetMapping("/{userId}")
    public List<CartResponseDTO> getCart(@PathVariable Long userId) {

        return cartItemRepository.findByUserId(userId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // CONVERTER
    private CartResponseDTO toDTO(CartItem item) {

        CartResponseDTO dto = new CartResponseDTO();
        dto.setCartItemId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getProduct().getPrice());

        return dto;
    }
}