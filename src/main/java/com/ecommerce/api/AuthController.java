package com.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.api.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // REGISTER API
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    // SIMPLE LOGIN (TEMP - JWT will replace this later)
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        return userService.findByUsername(request.getUsername())
                .map(user -> JwtUtil.generateToken(user.getUsername(), user.getRole()))
                .orElse("Invalid username");
    }
}