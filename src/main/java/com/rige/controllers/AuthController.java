package com.rige.controllers;

import com.rige.dto.request.LoginRequest;
import com.rige.dto.request.RegisterRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.services.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final IAuthService iAuthService;

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest userRequest) {
        return new ApiResponse(iAuthService.login(userRequest));
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody RegisterRequest registerRequest) {
        return new ApiResponse(iAuthService.register(registerRequest));
    }
}