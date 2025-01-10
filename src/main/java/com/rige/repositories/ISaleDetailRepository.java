package com.rige.repositories;

import com.rige.entities.SaleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleDetailRepository extends JpaRepository<SaleDetailEntity, Long> {
}
