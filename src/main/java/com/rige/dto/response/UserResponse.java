package com.rige.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String username;
    private boolean enabled;
    private EmployeeResponse employee;
}
