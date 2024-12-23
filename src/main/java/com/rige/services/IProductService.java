package com.rige.services;

import com.rige.dto.ProductDto;
import com.rige.dto.request.ProductRequest;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    void save(ProductRequest productRequest);
    List<ProductDto> findAll();
    List<ProductDto> findAllActive();
    ProductDto findById(Long id);
    void update(Long id, ProductRequest productRequest);
    void delete(Long id);
}
