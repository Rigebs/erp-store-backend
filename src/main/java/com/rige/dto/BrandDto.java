package com.rige.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandDto {
    private Long id;
    private String name;
    private String description;
    private boolean status;
    private boolean flag;
}