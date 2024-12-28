package com.rige.repositories;

import com.rige.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISupplierRepository extends JpaRepository<SupplierEntity, Long> {
    List<SupplierEntity> findByFlag(boolean flag);
    List<SupplierEntity> findByStatusAndFlag(boolean status, boolean flag);
}
