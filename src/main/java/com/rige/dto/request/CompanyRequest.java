package com.rige.dto.request;

import lombok.Data;

@Data
public class CompanyRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
}
