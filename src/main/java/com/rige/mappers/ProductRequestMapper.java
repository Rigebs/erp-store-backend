package com.rige.mappers;

import com.rige.dto.request.ProductRequest;
import com.rige.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper {

    public ProductRequest toRequest(Product product) {
        return ProductRequest.builder()
                .name(product.getName())
                .description(product.getDescription())
                .purchasePrice(product.getPurchasePrice())
                .salePrice(product.getSalePrice())
                .status(product.isStatus())
                .flag(product.isFlag())

                .brandId(product.getBrand() != null ? product.getBrand().getId() : null)
                .unitMeasureId(product.getUnitMeasure() != null ? product.getUnitMeasure().getId() : null)
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .lineId(product.getLine() != null ? product.getLine().getId() : null)
                .supplierId(product.getSupplier() != null ? product.getSupplier().getId() : null)

                .build();
    }
}