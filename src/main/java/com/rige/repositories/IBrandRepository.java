package com.rige.repositories;

import com.rige.entities.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    @EntityGraph("userEntity")
    Page<BrandEntity> findByFlagAndUserEntity_Id(boolean flag, Long id, Pageable pageable);

    @EntityGraph("userEntity")
    Page<BrandEntity> findByFlagAndStatusAndUserEntity_Id(boolean flag, boolean status, Long id, Pageable pageable);
}
