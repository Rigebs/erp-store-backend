package com.rige.services;

import com.rige.dto.request.LoginRequest;
import com.rige.dto.request.RegisterRequest;

public interface IAuthService {
    String login(LoginRequest userRequest);
    String register(RegisterRequest registerRequest);
}