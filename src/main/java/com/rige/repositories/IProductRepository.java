package com.rige.repositories;

import com.rige.entities.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    @EntityGraph(attributePaths = {"brandEntity", "categoryEntity", "unitMeasureEntity", "lineEntity", "supplierEntity"})
    List<ProductEntity> findByFlag(boolean flag);

    List<ProductEntity> findByStatusAndFlag(boolean status, boolean flag);
}
