package com.rige.repositories;

import com.rige.entities.InventoryMovementEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventoryMovementRepository extends JpaRepository<InventoryMovementEntity, Long>,
        JpaSpecificationExecutor<InventoryMovementEntity> {

    @NonNull
    @EntityGraph(attributePaths = {
            "product",
            "fromWarehouse",
            "toWarehouse",
    })
    Page<InventoryMovementEntity> findAll(Specification<InventoryMovementEntity> spec, @NonNull Pageable pageable);
}
