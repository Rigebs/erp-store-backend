package com.rige.services;

import com.rige.dto.request.CustomerRequest;
import com.rige.dto.response.CustomerResponse;
import com.rige.filters.CustomerFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
    CustomerResponse save(CustomerRequest request);

    Page<CustomerResponse> findAll(CustomerFilter filter, Pageable pageable);

    CustomerResponse findById(Long id);

    void update(Long id, CustomerRequest request);

    void toggleEnabled(Long id);
}