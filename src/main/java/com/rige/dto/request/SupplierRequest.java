package com.rige.dto.request;

import lombok.Data;

@Data
public class SupplierRequest {
    private String name;
    private String contactName;
    private String contactEmail;
    private String phoneNumber;
    private String address;
    private String website;
}
