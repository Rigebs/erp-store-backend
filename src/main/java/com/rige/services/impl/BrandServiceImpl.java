package com.rige.services.impl;

import com.rige.dto.BrandDto;
import com.rige.dto.request.BrandRequest;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.BrandMapper;
import com.rige.repositories.IBrandRepository;
import com.rige.services.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements IBrandService {

    private final IBrandRepository iBrandRepository;
    private final BrandMapper brandMapper;

    @Override
    public void save(BrandRequest brandRequest) {
        var brand = brandMapper.toDbo(brandRequest);
        brand.setStatus(true);
        brand.setFlag(true);
        iBrandRepository.save(brand);
    }

    @Override
    public Page<BrandDto> findAll(Long userId, Pageable pageable) {
        return brandMapper.toDtoList(iBrandRepository.findByFlagAndUserEntity_Id(true, userId, pageable));
    }

    @Override
    public Page<BrandDto> findAllActive(Long userId, Pageable pageable) {
        return brandMapper.toDtoList(iBrandRepository.findByFlagAndStatusAndUserEntity_Id(true, true, userId, pageable));
    }

    @Override
    public BrandDto findById(Long id) {
        return brandMapper.toDto(iBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id)));
    }

    @Override
    public void update(Long id, BrandRequest brandRequest) {
        var existingBrand = iBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id));

        existingBrand.setName(brandRequest.getName());
        existingBrand.setDescription(brandRequest.getDescription());

        iBrandRepository.save(existingBrand);
    }

    @Override
    public void delete(Long id) {
        var existingBrand = iBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id));
        existingBrand.setFlag(false);
        iBrandRepository.save(existingBrand);
    }

    @Override
    public void toggleStatus(Long id) {
        var existingBrand = iBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id));
        existingBrand.setStatus(!existingBrand.isStatus());
        iBrandRepository.save(existingBrand);
    }
}

