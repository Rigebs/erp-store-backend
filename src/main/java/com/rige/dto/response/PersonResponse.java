package com.rige.dto.response;

import lombok.Data;

@Data
public class PersonResponse {
    private Long id;
    private String name;
    private String paternalName;
    private String maternalName;
    private String email;
    private String phone;
    private String address;
}
