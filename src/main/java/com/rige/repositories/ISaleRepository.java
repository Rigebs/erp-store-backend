package com.rige.repositories;

import com.rige.entities.SaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {
    @EntityGraph(attributePaths = {"customer", "cashier"})
    List<SaleEntity> findByCashier_Id(Long id);

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"saleDetails", "saleDetails.product"})
    Optional<SaleEntity> findById(@NonNull Long id);

    @Override
    @NonNull
    @EntityGraph(attributePaths = {
            "saleDetails.product",
            "cashier"
    })
    Page<SaleEntity> findAll(@NonNull Pageable pageable);

    @Query("SELECT s.id FROM SaleEntity s")
    Page<Long> findAllIds(Pageable pageable);

    @Override
    @NonNull
    @EntityGraph(attributePaths = {
            "saleDetails.product",
            "cashier"
    })
    List<SaleEntity> findAllById(@NonNull Iterable ids);
}
