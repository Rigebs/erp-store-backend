package com.rige.repositories;

import com.rige.entities.SupplierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends JpaRepository<SupplierEntity, Long> {
    @EntityGraph("userEntity")
    Page<SupplierEntity> findByFlagAndUserEntity_Id(boolean flag, Long id, Pageable pageable);

    @EntityGraph("userEntity")
    Page<SupplierEntity> findByFlagAndStatusAndUserEntity_Id(boolean flag, boolean status, Long id, Pageable pageable);
}
