package com.rige.repositories;

import com.rige.entities.UnitMeasureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUnitMeasureRepository extends JpaRepository<UnitMeasureEntity, Long> {
}
