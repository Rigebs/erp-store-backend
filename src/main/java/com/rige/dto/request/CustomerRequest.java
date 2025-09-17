package com.rige.dto.request;

import lombok.Data;

@Data
public class CustomerRequest {
    private PersonRequest person;
    private CompanyRequest company;
}
