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

    @Query(value = "CALL delete_relationships(:id, :table_name)", nativeQuery = true)
    void deleteRelationship(@Param("id") Long id, @Param("table_name") String tableName);

    @EntityGraph(attributePaths = {"userEntity", "brandEntity", "categoryEntity", "unitMeasureEntity", "lineEntity", "supplierEntity", "imageEntity"})
    List<ProductEntity> findByFlagAndStatusAndUserEntity_Id(boolean flag, boolean status, Long id);

    @EntityGraph(attributePaths = {"userEntity", "brandEntity", "categoryEntity", "unitMeasureEntity", "lineEntity", "supplierEntity", "imageEntity"})
    List<ProductEntity> findByFlagAndUserEntity_Id(boolean flag, Long id);

}
