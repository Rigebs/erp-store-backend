package com.rige.dto.request;

import lombok.Data;

@Data
public class PersonRequest {
    private String name;
    private String paternalName;
    private String maternalName;
    private String email;
    private String phone;
    private String address;
}
