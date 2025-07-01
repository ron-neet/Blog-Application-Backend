package com.RonitCodes.Blog.Application.Controller;

import com.RonitCodes.Blog.Application.Config.CustomUserDetailsService;
import com.RonitCodes.Blog.Application.Dto.Auth.AuthRequest;
import com.RonitCodes.Blog.Application.Dto.Auth.AuthResponse;
import com.RonitCodes.Blog.Application.Utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication controller for handling login requests and generating JWT tokens.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * POST endpoint to authenticate user and return JWT token.
     * @return AuthResponse with JWT token or 401 error if authentication fails
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
        try {
            // Try to authenticate user with provided credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // If authentication is successful, load user and generate JWT token
            var userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtUtil.generateToken(userDetails.getUsername());

            // Return token wrapped in response DTO
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (AuthenticationException exception) {
            // Return 401 if authentication fails
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
