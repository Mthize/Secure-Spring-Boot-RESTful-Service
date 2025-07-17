package com.example.secureapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key for signing the JWT (keep this secret and secure in production!)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token validity period (1 hour here, adjust as needed)
    private final long expirationMillis = 1000 * 60 * 60;

    // Generate JWT token for given username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)                    // set username as subject
                .setIssuedAt(new Date())                 // current time
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))  // expire time
                .signWith(key)                           // sign with secret key
                .compact();
    }

    // Extract username from the token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate the token (checks signature and expiration)
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);  // will throw exception if invalid
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log token validation errors here if needed
            return false;
        }
    }
}

