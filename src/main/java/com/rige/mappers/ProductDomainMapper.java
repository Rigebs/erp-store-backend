package com.rige.mappers;

import com.rige.dto.request.ProductRequest;
import com.rige.entities.*;
import com.rige.models.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDomainMapper {

    public Product toDomain(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .quantity(productEntity.getQuantity())
                .purchasePrice(productEntity.getPurchasePrice())
                .salePrice(productEntity.getSalePrice())
                .status(productEntity.isStatus())
                .flag(productEntity.isFlag())
                .image(mapImage(productEntity.getImageEntity()))
                .brand(mapBrand(productEntity.getBrandEntity()))
                .category(mapCategory(productEntity.getCategoryEntity()))
                .unitMeasure(mapUnitMeasure(productEntity.getUnitMeasureEntity()))
                .line(mapLine(productEntity.getLineEntity()))
                .supplier(mapSupplier(productEntity.getSupplierEntity()))
                .build();
    }

    public List<Product> toDomainList(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public Product toDomain(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .quantity(productRequest.getQuantity())
                .purchasePrice(productRequest.getPurchasePrice())
                .salePrice(productRequest.getSalePrice())
                .image(productRequest.getImageId() != null ? Image.builder().id(productRequest.getImageId()).build() : null)
                .brand(productRequest.getBrandId() != null ? Brand.builder().id(productRequest.getBrandId()).build() : null)
                .category(productRequest.getCategoryId() != null ? Category.builder().id(productRequest.getCategoryId()).build() : null)
                .unitMeasure(productRequest.getUnitMeasureId() != null ? UnitMeasure.builder().id(productRequest.getUnitMeasureId()).build() : null)
                .line(productRequest.getLineId() != null ? Line.builder().id(productRequest.getLineId()).build() : null)
                .supplier(productRequest.getSupplierId() != null ? Supplier.builder().id(productRequest.getSupplierId()).build() : null)
                .build();
    }

    private Image mapImage(ImageEntity imageEntity) {
        return imageEntity != null ? Image.builder().id(imageEntity.getId()).secureUrl(imageEntity.getSecureUrl()).createdAt(imageEntity.getCreatedAt()).build() : null;
    }

    private Brand mapBrand(BrandEntity brandEntity) {
        return brandEntity != null ? Brand.builder().id(brandEntity.getId()).name(brandEntity.getName()).build() : null;
    }

    private Category mapCategory(CategoryEntity categoryEntity) {
        return categoryEntity != null ? Category.builder().id(categoryEntity.getId()).name(categoryEntity.getName()).build() : null;
    }

    private UnitMeasure mapUnitMeasure(UnitMeasureEntity unitMeasureEntity) {
        return unitMeasureEntity != null ? UnitMeasure.builder().id(unitMeasureEntity.getId()).abbreviation(unitMeasureEntity.getAbbreviation()).build() : null;
    }

    private Line mapLine(LineEntity lineEntity) {
        return lineEntity != null ? Line.builder().id(lineEntity.getId()).name(lineEntity.getName()).build() : null;
    }

    private Supplier mapSupplier(SupplierEntity supplierEntity) {
        return supplierEntity != null ? Supplier.builder().id(supplierEntity.getId()).name(supplierEntity.getName()).build() : null;
    }
}