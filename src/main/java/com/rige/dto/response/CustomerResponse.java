package com.rige.dto.response;

import lombok.Data;

@Data
public class CustomerResponse {
    private Long id;
    private boolean enabled;
    private PersonResponse person;
    private CompanyResponse company;
}
