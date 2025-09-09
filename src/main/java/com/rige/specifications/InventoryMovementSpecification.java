package com.rige.specifications;

import com.rige.entities.InventoryMovementEntity;
import com.rige.enums.MovementType;
import com.rige.filters.MovementFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class InventoryMovementSpecification {

    public static Specification<InventoryMovementEntity> build(MovementFilter filter) {
        return Specification.where(hasProductId(filter.getProductId()))
                .and(hasFromWarehouseId(filter.getFromWarehouseId()))
                .and(hasToWarehouseId(filter.getToWarehouseId()))
                .and(hasType(filter.getType()))
                .and(dateBetween(filter.getStartDate(), filter.getEndDate()));
    }

    private static Specification<InventoryMovementEntity> hasProductId(Long productId) {
        return (root, query, cb) ->
                productId == null ? null : cb.equal(root.get("product").get("id"), productId);
    }

    private static Specification<InventoryMovementEntity> hasFromWarehouseId(Long warehouseId) {
        return (root, query, cb) ->
                warehouseId == null ? null : cb.equal(root.get("fromWarehouse").get("id"), warehouseId);
    }

    private static Specification<InventoryMovementEntity> hasToWarehouseId(Long warehouseId) {
        return (root, query, cb) ->
                warehouseId == null ? null : cb.equal(root.get("toWarehouse").get("id"), warehouseId);
    }

    private static Specification<InventoryMovementEntity> hasType(MovementType type) {
        return (root, query, cb) ->
                type == null ? null : cb.equal(root.get("type"), type);
    }

    private static Specification<InventoryMovementEntity> dateBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, cb) -> {
            if (start != null && end != null) {
                return cb.between(root.get("date"), start, end);
            } else if (start != null) {
                return cb.greaterThanOrEqualTo(root.get("date"), start);
            } else if (end != null) {
                return cb.lessThanOrEqualTo(root.get("date"), end);
            }
            return null;
        };
    }
}