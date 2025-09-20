package com.rige.specifications;

import com.rige.entities.CustomerEntity;
import com.rige.entities.SaleEntity;
import com.rige.entities.UserEntity;
import com.rige.filters.SaleFilter;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import java.time.LocalDateTime;

public class SaleSpecification {

    public static Specification<SaleEntity> build(SaleFilter filter) {
        return textSearch(filter.getQuery())
                .and(dateBetween(filter.getStartDate(), filter.getEndDate()))
                .and(totalBetween(filter.getMinTotal(), filter.getMaxTotal()))
                .and(hasStatus(filter.getStatus()))
                .and(customerEquals(filter.getCustomerId()))
                .and(cashierEquals(filter.getCashierId()));
    }

    private static Specification<SaleEntity> textSearch(String text) {
        return (root, query, cb) -> {
            if (text == null || text.isBlank()) return null;
            String likeText = "%" + text.toLowerCase() + "%";
            Join<SaleEntity, CustomerEntity> customer = root.join("customer", JoinType.LEFT);
            Join<SaleEntity, UserEntity> cashier = root.join("cashier", JoinType.LEFT);
            return cb.or(
                    cb.like(cb.lower(customer.get("name")), likeText),
                    cb.like(cb.lower(cashier.get("username")), likeText)
            );
        };
    }

    private static Specification<SaleEntity> dateBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, cb) -> {
            if (start == null && end == null) return null;
            if (start != null && end != null) {
                return cb.between(root.get("dateTime"), start, end);
            }
            if (start != null) {
                return cb.greaterThanOrEqualTo(root.get("dateTime"), start);
            }
            return cb.lessThanOrEqualTo(root.get("dateTime"), end);
        };
    }

    private static Specification<SaleEntity> totalBetween(Double min, Double max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min != null && max != null) {
                return cb.between(root.get("total"), min, max);
            }
            if (min != null) {
                return cb.greaterThanOrEqualTo(root.get("total"), min);
            }
            return cb.lessThanOrEqualTo(root.get("total"), max);
        };
    }

    private static Specification<SaleEntity> hasStatus(Boolean status) {
        return (root, query, cb) -> {
            if (status == null) return null;
            return cb.equal(root.get("status"), status);
        };
    }

    private static Specification<SaleEntity> customerEquals(Long customerId) {
        return (root, query, cb) -> {
            if (customerId == null) return null;
            Join<SaleEntity, CustomerEntity> customer = root.join("customer", JoinType.LEFT);
            return cb.equal(customer.get("id"), customerId);
        };
    }

    private static Specification<SaleEntity> cashierEquals(Long cashierId) {
        return (root, query, cb) -> {
            if (cashierId == null) return null;
            Join<SaleEntity, UserEntity> cashier = root.join("cashier", JoinType.LEFT);
            return cb.equal(cashier.get("id"), cashierId);
        };
    }
}
