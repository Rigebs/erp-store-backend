package com.rige.services.impl;

import com.rige.dto.request.ProductRequest;
import com.rige.dto.response.ProductResponse;
import com.rige.entities.*;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.filters.ProductFilter;
import com.rige.mappers.IProductMapper;
import com.rige.repositories.IProductRepository;
import com.rige.services.IProductService;
import com.rige.specifications.ProductSpecification;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final IProductMapper productMapper;
    private final EntityManager entityManager;

    @Override
    public void save(ProductRequest request) {
        var entity = productMapper.toEntity(request);
        entity.setEnabled(true);
        entity.setFlag(true);

        applyRelations(entity, request, entityManager);

        productRepository.save(entity);
    }

    @Override
    public Page<ProductResponse> findAll(ProductFilter filter, Pageable pageable) {
        return productRepository.findAll(
                ProductSpecification.build(filter),
                pageable
        ).map(productMapper::toResponse);
    }

    @Override
    public ProductResponse findById(Long id) {
        var productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return productMapper.toResponse(productEntity);
    }

    @Override
    @Transactional
    public void update(Long id, ProductRequest request) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        productMapper.updateFromRequest(request, entity);

        applyRelations(entity, request, entityManager);

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

    private static void applyRelations(ProductEntity entity, ProductRequest request, EntityManager entityManager) {
        if (request.getCategoryId() != null) {
            entity.setCategory(entityManager.getReference(CategoryEntity.class, request.getCategoryId()));
        } else {
            entity.setCategory(null);
        }

        if (request.getBrandId() != null) {
            entity.setBrand(entityManager.getReference(BrandEntity.class, request.getBrandId()));
        } else {
            entity.setBrand(null);
        }

        if (request.getLineId() != null) {
            entity.setLine(entityManager.getReference(LineEntity.class, request.getLineId()));
        } else {
            entity.setLine(null);
        }

        if (request.getSupplierId() != null) {
            entity.setSupplier(entityManager.getReference(SupplierEntity.class, request.getSupplierId()));
        } else {
            entity.setSupplier(null);
        }

        if (request.getUnitMeasureId() != null) {
            entity.setUnitMeasure(entityManager.getReference(UnitMeasureEntity.class, request.getUnitMeasureId()));
        } else {
            entity.setUnitMeasure(null);
        }
    }
}