package com.ecommerce.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    // Secret key must be at least 256 bits for HS256
    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor("mySecretKey123mySecretKey123mySecretKey123".getBytes());

    // Generate Token
    public static String generateToken(String username, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SECRET_KEY)
                .compact();
    }
}