package com.RonitCodes.Blog.Application.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utility class for handling JWT generation, validation, and parsing.
 */
@Component
public class JwtUtil {

    // Secret key used to sign the JWT tokens
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token validity duration (1 hour in milliseconds)
    private final Long expirationMs = 3600000L;

    /**
     * Generates a new JWT token for a given username.
     * @return a signed JWT token as a string
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)                                // Set username as subject
                .setIssuedAt(new Date())                             // Token issue time (now)
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))  // Expiration time
                .signWith(key, SignatureAlgorithm.HS256)             // Sign with HMAC-SHA256
                .compact();                                          // Build the token string
    }

    /**
     * Extracts the username (subject) from the JWT token.
     * @return the username (subject)
     */
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    /**
     * Validates the token by parsing it and catching any exceptions.
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            parseClaims(token); // Will throw if invalid or expired
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Parses the JWT token and returns its claims.
     * @return the token claims
     */
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)              // Set the secret key used for signature validation
                .build()
                .parseClaimsJws(token)           // Parse and verify the JWT
                .getBody();                      // Return the claims (payload)
    }
}
