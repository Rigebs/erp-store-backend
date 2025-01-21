package com.rige.repositories;

import com.rige.entities.SupplierEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISupplierRepository extends JpaRepository<SupplierEntity, Long> {
    @EntityGraph("userEntity")
    List<SupplierEntity> findByFlagAndUserEntity_Id(boolean flag, Long id);

    @EntityGraph("userEntity")
    List<SupplierEntity> findByFlagAndStatusAndUserEntity_Id(boolean flag, boolean status, Long id);
}
