package com.rige.repositories;

import com.rige.entities.ProductEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    @NonNull
    @EntityGraph(attributePaths = {
            "brand",
            "line",
            "supplier",
            "unitMeasure",
            "category"
    })
    Page<ProductEntity> findAll(@NonNull Pageable pageable);
}
