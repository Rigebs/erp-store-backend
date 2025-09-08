package com.rige.services;

import com.rige.dto.request.BrandRequest;
import com.rige.dto.response.BrandResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandService {
    void save(BrandRequest brandRequest);
    Page<BrandResponse> findAll(Pageable pageable);
    BrandResponse findById(Long id);
    void update(Long id, BrandRequest brandRequest);
    void delete(Long id);
    void toggleStatus(Long id);
}