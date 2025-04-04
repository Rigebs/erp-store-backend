package com.rige.services;

import com.rige.dto.SupplierDto;
import com.rige.dto.request.SupplierRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISupplierService {
    void save(SupplierRequest supplierRequest);
    Page<SupplierDto> findAll(Long userId, Pageable pageable);
    Page<SupplierDto> findAllActive(Long userId, Pageable pageable);
    SupplierDto findById(Long id);
    void update(Long id, SupplierRequest supplierRequest);
    void delete(Long id);
    void toggleStatus(Long id);
}