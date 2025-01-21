package com.rige.repositories;

import com.rige.entities.UnitMeasureEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUnitMeasureRepository extends JpaRepository<UnitMeasureEntity, Long> {
    @EntityGraph("userEntity")
    List<UnitMeasureEntity> findByFlagAndUserEntity_Id(boolean flag, Long id);

    @EntityGraph("userEntity")
    List<UnitMeasureEntity> findByFlagAndStatusAndUserEntity_Id(boolean flag, boolean status, Long id);

}
