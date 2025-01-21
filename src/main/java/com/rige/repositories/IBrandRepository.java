package com.rige.repositories;

import com.rige.entities.BrandEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    @EntityGraph("userEntity")
    List<BrandEntity> findByFlagAndUserEntity_Id(boolean flag, Long id);
    @EntityGraph("userEntity")
    List<BrandEntity> findByFlagAndStatusAndUserEntity_Id(boolean flag, boolean status, Long id);
}
