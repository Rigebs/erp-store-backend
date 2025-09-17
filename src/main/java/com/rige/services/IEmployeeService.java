package com.rige.services;

import com.rige.dto.request.EmployeeRequest;
import com.rige.dto.response.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService {
    void save(EmployeeRequest request);
    Page<EmployeeResponse> findAll(Pageable pageable);
    EmployeeResponse findById(Long id);
    void update(Long id, EmployeeRequest request);
}