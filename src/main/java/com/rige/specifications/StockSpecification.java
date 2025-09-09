package com.rige.specifications;

import com.rige.entities.StockEntity;
import com.rige.filters.StockFilter;
import org.springframework.data.jpa.domain.Specification;

public class StockSpecification {

    public static Specification<StockEntity> build(StockFilter filter) {
        return Specification
                .where(hasProductId(filter.getProductId()))
                .and(hasWarehouseId(filter.getWarehouseId()))
                .and(quantityBetween(filter.getMinQuantity(), filter.getMaxQuantity()))
                .and(isBelowMinStock(filter.getBelowMin()));
    }

    private static Specification<StockEntity> hasProductId(Long productId) {
        return (root, query, cb) ->
                productId == null ? null : cb.equal(root.get("product").get("id"), productId);
    }

    private static Specification<StockEntity> hasWarehouseId(Long warehouseId) {
        return (root, query, cb) ->
                warehouseId == null ? null : cb.equal(root.get("warehouse").get("id"), warehouseId);
    }

    private static Specification<StockEntity> quantityBetween(Integer min, Integer max) {
        return (root, query, cb) -> {
            if (min != null && max != null) {
                return cb.between(root.get("quantity"), min, max);
            } else if (min != null) {
                return cb.greaterThanOrEqualTo(root.get("quantity"), min);
            } else if (max != null) {
                return cb.lessThanOrEqualTo(root.get("quantity"), max);
            }
            return null;
        };
    }

    private static Specification<StockEntity> isBelowMinStock(Boolean belowMin) {
        return (root, query, cb) -> {
            if (belowMin == null) return null;
            if (belowMin) {
                return cb.lessThan(root.get("quantity"), root.get("minQuantity"));
            } else {
                return cb.greaterThanOrEqualTo(root.get("quantity"), root.get("minQuantity"));
            }
        };
    }
}