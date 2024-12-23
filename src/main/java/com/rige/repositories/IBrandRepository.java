package com.rige.repositories;

import com.rige.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    List<BrandEntity> findByFlag(boolean flag);

}
