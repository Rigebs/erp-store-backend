package com.rige.services.impl;

import com.rige.dto.request.BrandRequest;
import com.rige.dto.response.BrandResponse;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.IBrandMapper;
import com.rige.repositories.IBrandRepository;
import com.rige.services.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements IBrandService {

    private final IBrandRepository brandRepository;
    private final IBrandMapper brandMapper;

    @Override
    public void save(BrandRequest brandRequest) {
        var brand = brandMapper.toEntity(brandRequest);
        brand.setEnabled(true);
        brand.setFlag(true);
        brandRepository.save(brand);
    }

    @Override
    public Page<BrandResponse> findAll(Pageable pageable) {
        var brands = brandRepository.findAll(pageable);
        return brands.map(brandMapper::toResponse);
    }

    @Override
    public BrandResponse findById(Long id) {
        return brandMapper.toResponse(brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id)));
    }

    @Override
    public void update(Long id, BrandRequest brandRequest) {
        var existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id));

        existingBrand.setName(brandRequest.getName());
        existingBrand.setDescription(brandRequest.getDescription());

        brandRepository.save(existingBrand);
    }

    @Override
    public void delete(Long id) {
        var existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id));
        existingBrand.setFlag(false);
        brandRepository.save(existingBrand);
    }

    @Override
    public void toggleStatus(Long id) {
        var existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id));
        existingBrand.setEnabled(!existingBrand.isEnabled());
        brandRepository.save(existingBrand);
    }
}

