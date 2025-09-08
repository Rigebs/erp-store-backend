package com.rige.services;

import com.rige.dto.request.SupplierRequest;
import com.rige.dto.response.SupplierResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISupplierService {
    void save(SupplierRequest supplierRequest);
    Page<SupplierResponse> findAll(Pageable pageable);
    SupplierResponse findById(Long id);
    void update(Long id, SupplierRequest supplierRequest);
    void delete(Long id);
    void toggleEnabled(Long id);
}