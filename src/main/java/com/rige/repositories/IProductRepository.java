package com.rige.repositories;

import com.rige.entities.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    @EntityGraph(attributePaths = {"imageEntity", "brandEntity", "categoryEntity", "unitMeasureEntity", "lineEntity", "supplierEntity"})
    List<ProductEntity> findByFlag(boolean flag);

    List<ProductEntity> findByStatusAndFlag(boolean status, boolean flag);

    @Query(value = "CALL delete_relationships(:id, :table_name)", nativeQuery = true)
    void deleteRelationship(@Param("id") Long id, @Param("table_name") String tableName);
}
