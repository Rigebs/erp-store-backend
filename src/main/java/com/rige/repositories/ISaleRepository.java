package com.rige.repositories;

import com.rige.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {
}
