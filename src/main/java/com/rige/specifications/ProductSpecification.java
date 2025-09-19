package com.rige.specifications;

import com.rige.entities.*;
import com.rige.filters.ProductFilter;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<ProductEntity> build(ProductFilter filter) {
        return textSearch(filter.getQuery())
                .and(categoryEquals(filter.getCategoryId()))
                .and(brandEquals(filter.getBrandId()))
                .and(supplierEquals(filter.getSupplierId()))
                .and(unitMeasureEquals(filter.getUnitMeasureId()))
                .and(lineEquals(filter.getLineId()))
                .and(isEnabled(filter.getEnabled()))
                .and(hasFlag(filter.getFlag()))
                .and(priceBetween(filter.getMinPrice(), filter.getMaxPrice()));
    }

    private static Specification<ProductEntity> textSearch(String text) {
        return (root, query, cb) -> {
            if (text == null || text.isBlank()) return null;
            String likeText = "%" + text.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("name")), likeText),
                    cb.like(cb.lower(root.get("description")), likeText)
            );
        };
    }

    private static Specification<ProductEntity> categoryEquals(Long categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) return null;
            Join<ProductEntity, CategoryEntity> category = root.join("category", JoinType.LEFT);
            return cb.equal(category.get("id"), categoryId);
        };
    }

    private static Specification<ProductEntity> brandEquals(Long brandId) {
        return (root, query, cb) -> {
            if (brandId == null) return null;
            Join<ProductEntity, BrandEntity> brand = root.join("brand", JoinType.LEFT);
            return cb.equal(brand.get("id"), brandId);
        };
    }

    private static Specification<ProductEntity> supplierEquals(Long supplierId) {
        return (root, query, cb) -> {
            if (supplierId == null) return null;
            Join<ProductEntity, SupplierEntity> supplier = root.join("supplier", JoinType.LEFT);
            return cb.equal(supplier.get("id"), supplierId);
        };
    }

    private static Specification<ProductEntity> unitMeasureEquals(Long unitMeasureId) {
        return (root, query, cb) -> {
            if (unitMeasureId == null) return null;
            Join<ProductEntity, UnitMeasureEntity> unitMeasure = root.join("unitMeasure", JoinType.LEFT);
            return cb.equal(unitMeasure.get("id"), unitMeasureId);
        };
    }

    private static Specification<ProductEntity> lineEquals(Long lineId) {
        return (root, query, cb) -> {
            if (lineId == null) return null;
            Join<ProductEntity, LineEntity> line = root.join("line", JoinType.LEFT);
            return cb.equal(line.get("id"), lineId);
        };
    }

    private static Specification<ProductEntity> isEnabled(Boolean enabled) {
        return (root, query, cb) -> {
            if (enabled == null) return null;
            return cb.equal(root.get("enabled"), enabled);
        };
    }

    private static Specification<ProductEntity> hasFlag(Boolean flag) {
        return (root, query, cb) -> {
            if (flag == null) return null;
            return cb.equal(root.get("flag"), flag);
        };
    }

    private static Specification<ProductEntity> priceBetween(Double min, Double max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            Path<Double> field = root.get("salePrice");
            if (min != null && max != null) {
                return cb.between(field, min, max);
            }
            if (min != null) {
                return cb.greaterThanOrEqualTo(field, min);
            }
            return cb.lessThanOrEqualTo(field, max);
        };
    }
}