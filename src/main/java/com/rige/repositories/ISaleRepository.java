package com.rige.repositories;

import com.rige.entities.SaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISaleRepository extends JpaRepository<SaleEntity, Long>, JpaSpecificationExecutor<SaleEntity> {
    @EntityGraph(attributePaths = {"customer", "cashier"})
    List<SaleEntity> findByCashier_Id(Long id);

    @NonNull
    @Override
    @EntityGraph(attributePaths = {
            "saleDetails",
            "saleDetails.product",
            "cashier.employee",
    })
    Optional<SaleEntity> findById(@NonNull Long id);

    @Query("SELECT s.id FROM SaleEntity s")
    Page<Long> findAllIds(Specification<SaleEntity> spec, Pageable pageable);

    @Query("""
            select distinct s from SaleEntity s
            left join fetch s.saleDetails sd
            left join fetch sd.product p
            left join fetch s.cashier c
            left join fetch c.warehouse w
            left join fetch c.roles r
            left join fetch c.employee e
            left join fetch e.person
            left join fetch s.customer cus
            left join fetch cus.person
            left join fetch cus.company
            where s.id in :ids
        """)
    List<SaleEntity> findAllWithDetailsAndPerson(@Param("ids") Iterable<Long> ids);

    @EntityGraph(attributePaths = {
            "saleDetails",
            "saleDetails.product",
            "cashier",
            "cashier.roles",
            "cashier.employee",
            "cashier.employee.person",
            "customer",
            "customer.person",
            "customer.company"
    })
    List<SaleEntity> findAllById(Iterable<Long> ids);
}
