package com.rige.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierDto {
    private Long id;
    private String name;
    private String contactName;
    private String contactEmail;
    private String phoneNumber;
    private String address;
    private String website;
    private boolean status;
    private boolean flag;
}