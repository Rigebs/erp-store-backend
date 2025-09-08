package com.rige.services;

import com.rige.dto.request.ProductRequest;
import com.rige.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    void save(ProductRequest productRequest);
    Page<ProductResponse> findAll(Pageable pageable);
    ProductResponse findById(Long id);
    void update(Long id, ProductRequest productRequest);
    void delete(Long id);
    void toggleEnabled(Long id);
}
