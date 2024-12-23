package com.rige.services.impl;

import com.rige.dto.SupplierDto;
import com.rige.mappers.SupplierMapper;
import com.rige.repositories.ISupplierRepository;
import com.rige.services.ISupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierServiceImpl implements ISupplierService {

    private final ISupplierRepository iSupplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public List<SupplierDto> findAllActive() {
        return supplierMapper.toDtoList(iSupplierRepository.findByFlag(true));
    }
}
