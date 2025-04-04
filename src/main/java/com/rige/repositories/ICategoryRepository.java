package com.rige.repositories;

import com.rige.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @EntityGraph("userEntity")
    Page<CategoryEntity> findByFlagAndUserEntity_Id(boolean flag, Long id, Pageable pageable);

    @EntityGraph("userEntity")
    Page<CategoryEntity> findByFlagAndStatusAndUserEntity_Id(boolean flag, boolean status, Long id, Pageable pageable);
}
