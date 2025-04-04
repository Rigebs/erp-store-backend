package com.rige.repositories;

import com.rige.entities.UnitMeasureEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUnitMeasureRepository extends JpaRepository<UnitMeasureEntity, Long> {
    @EntityGraph("userEntity")
    Page<UnitMeasureEntity> findByFlagAndUserEntity_Id(boolean flag, Long id, Pageable pageable);

    @EntityGraph("userEntity")
    Page<UnitMeasureEntity> findByFlagAndStatusAndUserEntity_Id(boolean flag, boolean status, Long id, Pageable pageable);
}
