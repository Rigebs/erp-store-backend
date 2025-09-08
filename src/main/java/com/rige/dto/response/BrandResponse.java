package com.rige.dto.response;

import lombok.Data;

@Data
public class BrandResponse {
    private Long id;
    private String name;
    private String description;
    private boolean status;
    private boolean flag;
}