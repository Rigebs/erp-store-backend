package com.rige.services.impl;

import com.rige.dto.request.SupplierRequest;
import com.rige.dto.response.SupplierResponse;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.ISupplierMapper;
import com.rige.repositories.ISupplierRepository;
import com.rige.services.ISupplierService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupplierServiceImpl implements ISupplierService {

    private final ISupplierRepository supplierRepository;
    private final ISupplierMapper supplierMapper;

    @Override
    public void save(SupplierRequest supplierRequest) {
        var supplier = supplierMapper.toEntity(supplierRequest);
        supplier.setEnabled(true);
        supplier.setFlag(true);
        supplierRepository.save(supplier);
    }

    @Override
    public Page<SupplierResponse> findAll(Pageable pageable) {
        var suppliers = supplierRepository.findAll(pageable);
        return suppliers.map(supplierMapper::toResponse);
    }

    @Override
    public SupplierResponse findById(Long id) {
        return supplierMapper.toResponse(supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id)));
    }

    @Override
    public void update(Long id, SupplierRequest supplierRequest) {
        var existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));

        existingSupplier.setName(supplierRequest.getName());
        existingSupplier.setContactName(supplierRequest.getContactName());
        existingSupplier.setContactEmail(supplierRequest.getContactEmail());
        existingSupplier.setPhoneNumber(supplierRequest.getPhoneNumber());
        existingSupplier.setAddress(supplierRequest.getAddress());
        existingSupplier.setWebsite(supplierRequest.getWebsite());

        supplierRepository.save(existingSupplier);
    }

    @Override
    public void delete(Long id) {
        var existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
        existingSupplier.setFlag(false);
        supplierRepository.save(existingSupplier);
    }

    @Override
    public void toggleEnabled(Long id) {
        var existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
        existingSupplier.setEnabled(!existingSupplier.isEnabled());
        supplierRepository.save(existingSupplier);
    }
}