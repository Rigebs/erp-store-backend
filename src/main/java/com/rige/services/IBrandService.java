package com.rige.services;

import com.rige.dto.BrandDto;
import com.rige.dto.request.BrandRequest;
import com.rige.models.Brand;

import java.util.List;

public interface IBrandService {
    void save(BrandRequest brandRequest);
    List<BrandDto> findAll();
    List<BrandDto> findAllActive();
    Brand findById(Long id);
    void update(Long id, BrandRequest brandRequest);
    void delete(Long id);
    void toggleStatus(Long id);

}