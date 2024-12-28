package com.rige.repositories;

import com.rige.entities.UnitMeasureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUnitMeasureRepository extends JpaRepository<UnitMeasureEntity, Long> {
    List<UnitMeasureEntity> findByFlag(boolean flag);
    List<UnitMeasureEntity> findByStatusAndFlag(boolean status, boolean flag);
}
