package com.rige.dto.request;

import com.rige.enums.EmploymentStatus;
import com.rige.enums.EmploymentType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeRequest {
    private LocalDate hireDate;
    private LocalDate terminationDate;
    private String jobTitle;
    private BigDecimal salary;
    private EmploymentStatus employmentStatus;
    private EmploymentType employmentType;
    private PersonRequest person;
}