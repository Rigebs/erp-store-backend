package com.rige.mappers;

import com.rige.entities.*;
import com.rige.models.*;
import org.springframework.stereotype.Component;

@Component
public class ProductDboMapper {

    public ProductEntity toDbo(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .purchasePrice(product.getPurchasePrice())
                .salePrice(product.getSalePrice())
                .status(product.isStatus())
                .flag(product.isFlag())

                .brandEntity(mapBrandToEntity(product.getBrand()))
                .categoryEntity(mapCategoryToEntity(product.getCategory()))
                .unitMeasureEntity(mapUnitMeasureToEntity(product.getUnitMeasure()))
                .lineEntity(mapLineToEntity(product.getLine()))
                .supplierEntity(mapSupplierToEntity(product.getSupplier()))

                .build();
    }

    private BrandEntity mapBrandToEntity(Brand brand) {
        if (brand == null) {
            return null;
        }
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brand.getId());
        return brandEntity;
    }

    private CategoryEntity mapCategoryToEntity(Category category) {
        if (category == null) {
            return null;
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        return categoryEntity;
    }

    private UnitMeasureEntity mapUnitMeasureToEntity(UnitMeasure unitMeasure) {
        if (unitMeasure == null) {
            return null;
        }
        UnitMeasureEntity unitMeasureEntity = new UnitMeasureEntity();
        unitMeasureEntity.setId(unitMeasure.getId());
        return unitMeasureEntity;
    }

    private LineEntity mapLineToEntity(Line line) {
        if (line == null) {
            return null;
        }
        LineEntity lineEntity = new LineEntity();
        lineEntity.setId(line.getId());
        return lineEntity;
    }

    private SupplierEntity mapSupplierToEntity(Supplier supplier) {
        if (supplier == null) {
            return null;
        }
        SupplierEntity supplierEntity = new SupplierEntity();
        supplierEntity.setId(supplier.getId());
        return supplierEntity;
    }
}