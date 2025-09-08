package com.rige.services.impl;

import com.rige.dto.request.ProductRequest;
import com.rige.dto.response.ProductResponse;
import com.rige.entities.ProductEntity;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.IProductMapper;
import com.rige.repositories.IProductRepository;
import com.rige.services.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final IProductMapper productMapper;

    @Override
    public void save(ProductRequest request) {
        var entity = productMapper.toEntity(request);
        entity.setEnabled(true);
        entity.setFlag(true);
        productRepository.save(entity);
    }

    @Override
    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toResponse);
    }

    @Override
    public ProductResponse findById(Long id) {
        var productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return productMapper.toResponse(productEntity);
    }

    @Override
    public void update(Long id, ProductRequest request) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        productMapper.updateFromRequest(request, entity);
        productRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        existingProduct.setFlag(false);
        productRepository.save(existingProduct);
    }

    @Override
    public void toggleEnabled(Long id) {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        existingProduct.setEnabled(!existingProduct.isEnabled());
        productRepository.save(existingProduct);
    }
}