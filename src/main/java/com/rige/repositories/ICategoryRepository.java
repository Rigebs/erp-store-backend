package com.rige.repositories;

import com.rige.entities.CategoryEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @EntityGraph("userEntity")
    List<CategoryEntity> findByFlagAndUserEntity_Id(boolean flag, Long id);

    @EntityGraph("userEntity")
    List<CategoryEntity> findByFlagAndStatusAndUserEntity_Id(boolean flag, boolean status, Long id);

}
