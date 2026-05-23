package com.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register new user
    public User registerUser(RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // 👉 IMPORTANT: default role
        user.setRole("USER");

        return userRepository.save(user);
    }

    // Find user by username (for login step)
    public Optional<User> findByUsername(String username) {
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
}