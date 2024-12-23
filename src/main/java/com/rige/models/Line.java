package com.rige.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Line {
    private Long id;
    private String name;
    private String description;
    private boolean status;
    private boolean flag;
}
