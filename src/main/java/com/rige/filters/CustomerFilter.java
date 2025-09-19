package com.rige.filters;

import lombok.Data;

@Data
public class CustomerFilter {
    private String query;
    private Boolean enabled;
}
