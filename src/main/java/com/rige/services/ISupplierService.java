package com.rige.services;

import com.rige.dto.SupplierDto;

import java.util.List;

public interface ISupplierService {
    List<SupplierDto> findAllActive();
}
