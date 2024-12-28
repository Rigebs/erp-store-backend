package com.rige.services.impl;

import com.rige.dto.ProductDto;
import com.rige.dto.request.ProductRequest;
import com.rige.entities.*;
import com.rige.exceptions.ResourceNotFoundException;
import com.rige.mappers.ProductDboMapper;
import com.rige.mappers.ProductDomainMapper;
import com.rige.mappers.ProductDtoMapper;
import com.rige.models.Product;
import com.rige.repositories.IProductRepository;
import com.rige.services.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository iProductRepository;
    private final ProductDtoMapper productDtoMapper;
    private final ProductDomainMapper productDomainMapper;
    private final ProductDboMapper productDboMapper;

    @Override
    public void save(ProductRequest productRequest) {
        var product = productDomainMapper.toDomain(productRequest);
        product.setStatus(true);
        product.setFlag(true);
        iProductRepository.save(productDboMapper.toDbo(product));
    }

    @Override
    public List<ProductDto> findAll() {
        var products = productDomainMapper.toDomainList(iProductRepository.findByFlag(true));
        return productDtoMapper.toDtoList(products);
    }

    @Override
    public List<ProductDto> findAllActive() {
        return productDtoMapper.toDtoList(productDomainMapper.toDomainList(iProductRepository.findByStatusAndFlag(true, true)));
    }

    @Override
    public Product findById(Long id) {
        var productEntity = iProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        System.out.println("PRODUCT: " + productEntity);
        return productDomainMapper.toDomain(productEntity);
    }

    @Override
    public void update(Long id, ProductRequest productRequest) {
        ProductEntity existingProduct = iProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPurchasePrice(productRequest.getPurchasePrice());
        existingProduct.setSalePrice(productRequest.getSalePrice());

        existingProduct.setBrandEntity(productRequest.getBrandId() != null ? new BrandEntity(productRequest.getBrandId()) : null);
        existingProduct.setCategoryEntity(productRequest.getCategoryId() != null ? new CategoryEntity(productRequest.getCategoryId()) : null);
        existingProduct.setUnitMeasureEntity(productRequest.getUnitMeasureId() != null ? new UnitMeasureEntity(productRequest.getUnitMeasureId()) : null);
        existingProduct.setLineEntity(productRequest.getLineId() != null ? new LineEntity(productRequest.getLineId()) : null);
        existingProduct.setSupplierEntity(productRequest.getSupplierId() != null ? new SupplierEntity(productRequest.getSupplierId()) : null);

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
        existingProduct.setStatus(!existingProduct.isStatus());
        iProductRepository.save(existingProduct);
    }
}