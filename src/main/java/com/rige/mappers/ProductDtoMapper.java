package com.rige.mappers;

import com.rige.dto.ProductDto;
import com.rige.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDtoMapper {

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .purchasePrice(product.getPurchasePrice())
                .salePrice(product.getSalePrice())
                .status(product.isStatus())
                .flag(product.isFlag())
                .secureUrl(product.getImage() != null ? product.getImage().getSecureUrl() : null)
                .brandName(product.getBrand() != null ? product.getBrand().getName() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .unitMeasureAbbreviation(product.getUnitMeasure() != null ? product.getUnitMeasure().getAbbreviation() : null)
                .lineName(product.getLine() != null ? product.getLine().getName() : null)
                .supplierName(product.getSupplier() != null ? product.getSupplier().getName() : null)
                .build();
    }

    public List<ProductDto> toDtoList(List<Product> products) {
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}