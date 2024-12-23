package com.rige.repositories;

import com.rige.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByFlag(boolean flag);
}
