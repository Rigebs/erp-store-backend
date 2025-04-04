package com.rige.services;

import com.rige.dto.ProductDto;
import com.rige.dto.request.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    void save(ProductRequest productRequest);
    Page<ProductDto> findAll(Long userId, Pageable pageable);
    Page<ProductDto> findAllActive(Long userId, Pageable pageable);
    ProductDto findById(Long id);
    void update(Long id, ProductRequest productRequest);
    void delete(Long id);
    void toggleStatus(Long id);
    void deleteRelationships(Long id, String table);
}
