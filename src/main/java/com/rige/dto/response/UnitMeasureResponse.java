package com.rige.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnitMeasureResponse {
    private Long id;
    private String name;
    private String abbreviation;
    private String description;
    private boolean status;
    private boolean flag;
}