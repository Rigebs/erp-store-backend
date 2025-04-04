package com.rige.services;

import com.rige.dto.BrandDto;
import com.rige.dto.request.BrandRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandService {
    void save(BrandRequest brandRequest);
    Page<BrandDto> findAll(Long userId, Pageable pageable);
    Page<BrandDto> findAllActive(Long userId, Pageable pageable);
    BrandDto findById(Long id);
    void update(Long id, BrandRequest brandRequest);
    void delete(Long id);
    void toggleStatus(Long id);

}