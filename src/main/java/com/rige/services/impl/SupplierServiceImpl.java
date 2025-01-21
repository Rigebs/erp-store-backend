package com.rige.services.impl;

import com.rige.dto.SupplierDto;
import com.rige.dto.request.SupplierRequest;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.SupplierMapper;
import com.rige.models.Supplier;
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
    public void save(SupplierRequest supplierRequest) {
        var supplier = supplierMapper.toDbo(supplierRequest);
        supplier.setStatus(true);
        supplier.setFlag(true);
        iSupplierRepository.save(supplier);
    }

    @Override
    public List<SupplierDto> findAll(Long userId) {
        return supplierMapper.toDtoList(iSupplierRepository.findByFlagAndUserEntity_Id(true, userId));
    }

    @Override
    public List<SupplierDto> findAllActive(Long userId) {
        return supplierMapper.toDtoList(iSupplierRepository.findByFlagAndStatusAndUserEntity_Id(true, true, userId));
    }

    @Override
    public Supplier findById(Long id) {
        return supplierMapper.toDomain(iSupplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id)));
    }

    @Override
    public void update(Long id, SupplierRequest supplierRequest) {
        var existingSupplier = iSupplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));

        existingSupplier.setName(supplierRequest.getName());
        existingSupplier.setContactName(supplierRequest.getContactName());
        existingSupplier.setContactEmail(supplierRequest.getContactEmail());
        existingSupplier.setPhoneNumber(supplierRequest.getPhoneNumber());
        existingSupplier.setAddress(supplierRequest.getAddress());
        existingSupplier.setWebsite(supplierRequest.getWebsite());

        iSupplierRepository.save(existingSupplier);
    }

    @Override
    public void delete(Long id) {
        var existingSupplier = iSupplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
        existingSupplier.setFlag(false);
        iSupplierRepository.save(existingSupplier);
    }

    @Override
    public void toggleStatus(Long id) {
        var existingSupplier = iSupplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
        existingSupplier.setStatus(!existingSupplier.isStatus());
        iSupplierRepository.save(existingSupplier);
    }
}
