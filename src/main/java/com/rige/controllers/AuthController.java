package com.rige.controllers;

import com.rige.dto.request.LoginRequest;
import com.rige.dto.request.RegisterRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.TokenResponse;
import com.rige.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody LoginRequest userRequest) {
        TokenResponse token = new TokenResponse(authService.login(userRequest));
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Login successful", token)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User registered successfully", null));
    }
}