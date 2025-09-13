package com.rige.dto.response;

import lombok.Data;

@Data
public class CompanyResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
}
