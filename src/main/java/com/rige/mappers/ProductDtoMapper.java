package com.rige.mappers;

import com.rige.dto.ProductDto;
import com.rige.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDtoMapper {

    public ProductDto toDto(Product product) {
        System.out.println("PRODUCT: " + product);
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .purchasePrice(product.getPurchasePrice())
                .salePrice(product.getSalePrice())
                .status(product.isStatus())
                .flag(product.isFlag())
                .brandName(product.getBrand() != null ? product.getBrand().getName() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .unitMeasureName(product.getUnitMeasure() != null ? product.getUnitMeasure().getName() : null)
                .lineName(product.getLine() != null ? product.getLine().getName() : null)
                .supplierName(product.getSupplier() != null ? product.getSupplier().getName() : null)
                .build();
    }

    public List<ProductDto> toDtoList(List<Product> products) {
        System.out.println("LISTA MODEL: " + products);
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}