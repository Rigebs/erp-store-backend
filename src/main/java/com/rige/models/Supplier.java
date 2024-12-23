package com.rige.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Supplier {
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
