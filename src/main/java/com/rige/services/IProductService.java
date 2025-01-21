package com.rige.services;

import com.rige.dto.ProductDto;
import com.rige.dto.request.ProductRequest;
import com.rige.models.Product;

import java.util.List;

public interface IProductService {
    void save(ProductRequest productRequest);
    List<ProductDto> findAll(Long userId);
    List<ProductDto> findAllActive(Long userId);
    Product findById(Long id);
    void update(Long id, ProductRequest productRequest);
    void delete(Long id);
    void toggleStatus(Long id);
    void deleteRelationships(Long id, String table);
}
