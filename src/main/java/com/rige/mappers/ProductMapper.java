package com.rige.mappers;

import com.rige.dto.ProductDto;
import com.rige.dto.request.ProductRequest;
import com.rige.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public ProductDto toDto(ProductEntity product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .purchasePrice(product.getPurchasePrice())
                .salePrice(product.getSalePrice())
                .status(product.isStatus())
                .flag(product.isFlag())
                .secureUrl(product.getImageEntity() != null ? product.getImageEntity().getSecureUrl() : null)
                .brandName(product.getBrandEntity() != null ? product.getBrandEntity().getName() : null)
                .categoryName(product.getCategoryEntity() != null ? product.getCategoryEntity().getName() : null)
                .unitMeasureAbbreviation(product.getUnitMeasureEntity() != null ? product.getUnitMeasureEntity().getAbbreviation() : null)
                .lineName(product.getLineEntity() != null ? product.getLineEntity().getName() : null)
                .supplierName(product.getSupplierEntity() != null ? product.getSupplierEntity().getName() : null)
                .build();
    }

    public Page<ProductDto> toDtoList(Page<ProductEntity> products) {
        return products.map(this::toDto);
    }

    public ProductEntity toEntity(ProductRequest productRequest) {
        return ProductEntity.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .quantity(productRequest.getQuantity())
                .purchasePrice(productRequest.getPurchasePrice())
                .salePrice(productRequest.getSalePrice())
                .flag(true)
                .status(true)
                .userEntity(UserEntity.builder().id(productRequest.getUserId()).build())
                .imageEntity(productRequest.getImageId() != null ? ImageEntity.builder().id(productRequest.getImageId()).build() : null)
                .brandEntity(productRequest.getBrandId() != null ? BrandEntity.builder().id(productRequest.getBrandId()).build() : null)
                .categoryEntity(productRequest.getCategoryId() != null ? CategoryEntity.builder().id(productRequest.getCategoryId()).build() : null)
                .unitMeasureEntity(productRequest.getUnitMeasureId() != null ? UnitMeasureEntity.builder().id(productRequest.getUnitMeasureId()).build() : null)
                .lineEntity(productRequest.getLineId() != null ? LineEntity.builder().id(productRequest.getLineId()).build() : null)
                .supplierEntity(productRequest.getSupplierId() != null ? SupplierEntity.builder().id(productRequest.getSupplierId()).build() : null)
                .build();
    }
}