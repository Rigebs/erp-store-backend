package com.rige.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}