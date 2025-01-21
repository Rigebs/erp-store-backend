package com.rige.services;

import com.rige.dto.BrandDto;
import com.rige.dto.request.BrandRequest;
import com.rige.models.Brand;

import java.util.List;

public interface IBrandService {
    void save(BrandRequest brandRequest);
    List<BrandDto> findAll(Long userId);
    List<BrandDto> findAllActive(Long userId);
    Brand findById(Long id);
    void update(Long id, BrandRequest brandRequest);
    void delete(Long id);
    void toggleStatus(Long id);

}