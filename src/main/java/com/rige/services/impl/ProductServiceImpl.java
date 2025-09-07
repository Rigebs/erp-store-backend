package com.rige.services.impl;

import com.rige.dto.ProductDto;
import com.rige.dto.request.ProductRequest;
import com.rige.entities.*;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.ProductMapper;
import com.rige.repositories.IProductRepository;
import com.rige.services.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository iProductRepository;
    private final ProductMapper productMapper;

    @Override
    public void save(ProductRequest productRequest) {
        iProductRepository.save(productMapper.toEntity(productRequest));
    }

    @Override
    public Page<ProductDto> findAll(Long userId, Pageable pageable) {
        return productMapper.toDtoList(iProductRepository.findAll(pageable));
    }

    @Override
    public Page<ProductDto> findAllActive(Long userId, Pageable pageable) {
        return productMapper.toDtoList(iProductRepository.findAll(pageable));
    }

    @Override
    public ProductDto findById(Long id) {
        var productEntity = iProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return productMapper.toDto(productEntity);
    }

    @Override
    public void update(Long id, ProductRequest productRequest) {
        ProductEntity existingProduct = iProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPurchasePrice(productRequest.getPurchasePrice());
        existingProduct.setSalePrice(productRequest.getSalePrice());

        existingProduct.setBrandEntity(
                productRequest.getBrandId() != null
                        ? BrandEntity.builder().id(productRequest.getBrandId()).build()
                        : null
        );
        existingProduct.setCategoryEntity(
                productRequest.getCategoryId() != null
                        ? CategoryEntity.builder().id(productRequest.getCategoryId()).build()
                        : null
        );
        existingProduct.setUnitMeasureEntity(
                productRequest.getUnitMeasureId() != null
                        ? UnitMeasureEntity.builder().id(productRequest.getUnitMeasureId()).build()
                        : null
        );
        existingProduct.setLineEntity(
                productRequest.getLineId() != null
                        ? LineEntity.builder().id(productRequest.getLineId()).build()
                        : null
        );
        existingProduct.setSupplierEntity(
                productRequest.getSupplierId() != null
                        ? SupplierEntity.builder().id(productRequest.getSupplierId()).build()
                        : null
        );

        iProductRepository.save(existingProduct);
    }

    @Override
    public void delete(Long id) {
        ProductEntity existingProduct = iProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        existingProduct.setFlag(false);
        iProductRepository.save(existingProduct);
    }

    @Override
    public void toggleStatus(Long id) {
        ProductEntity existingProduct = iProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        existingProduct.setEnabled(!existingProduct.isEnabled());
        iProductRepository.save(existingProduct);
    }

    @Override
    public void deleteRelationships(Long id, String tableName) {
        try {

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la relación: " + e);
        }
    }

}