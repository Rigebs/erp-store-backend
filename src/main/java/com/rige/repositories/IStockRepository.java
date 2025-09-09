package com.rige.repositories;

import com.rige.entities.ProductEntity;
import com.rige.entities.StockEntity;
import com.rige.entities.WarehouseEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
IStockRepository extends JpaRepository<StockEntity, Long>, JpaSpecificationExecutor<StockEntity> {

    @NonNull
    @EntityGraph(attributePaths = {
            "product",
            "warehouse",
    })
    Page<StockEntity> findAll(Specification<StockEntity> spec, @NonNull Pageable pageable);

    Optional<StockEntity> findByProductAndWarehouse(ProductEntity product, WarehouseEntity entity);
}
