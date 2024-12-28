package com.rige.services;

import com.rige.dto.SupplierDto;
import com.rige.dto.request.SupplierRequest;
import com.rige.models.Supplier;

import java.util.List;

public interface ISupplierService {
    void save(SupplierRequest supplierRequest);
    List<SupplierDto> findAll();
    List<SupplierDto> findAllActive();
    Supplier findById(Long id);
    void update(Long id, SupplierRequest supplierRequest);
    void delete(Long id);
    void toggleStatus(Long id);
}