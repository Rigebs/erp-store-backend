package com.rige.specifications;

import com.rige.entities.*;
import com.rige.filters.CustomerFilter;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {

    public static Specification<CustomerEntity> build(CustomerFilter filter) {
        return textSearch(filter.getQuery())
                .and(isEnabled(filter.getEnabled()));
    }

    private static Specification<CustomerEntity> textSearch(String text) {
        return (root, query, cb) -> {
            if (text == null || text.isBlank()) return null;
            String likeText = "%" + text.toLowerCase() + "%";

            Join<CustomerEntity, PersonEntity> person = root.join("person", JoinType.LEFT);
            Join<CustomerEntity, CompanyEntity> company = root.join("company", JoinType.LEFT);

            return cb.or(
                    cb.like(cb.lower(person.get("name")), likeText),
                    cb.like(cb.lower(person.get("paternalName")), likeText),
                    cb.like(cb.lower(person.get("maternalName")), likeText),
                    cb.like(cb.lower(person.get("email")), likeText),
                    cb.like(cb.lower(person.get("phone")), likeText),
                    cb.like(cb.lower(person.get("address")), likeText),

                    cb.like(cb.lower(company.get("name")), likeText),
                    cb.like(cb.lower(company.get("email")), likeText),
                    cb.like(cb.lower(company.get("phone")), likeText),
                    cb.like(cb.lower(company.get("address")), likeText)
            );
        };
    }

    private static Specification<CustomerEntity> isEnabled(Boolean enabled) {
        return (root, query, cb) -> {
            if (enabled == null) return null;
            return cb.equal(root.get("enabled"), enabled);
        };
    }
}
