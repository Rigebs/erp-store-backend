package com.rige.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnitMeasureDto {
    private Long id;
    private String name;
    private String abbreviation;
    private String description;
    private boolean status;
    private boolean flag;
}