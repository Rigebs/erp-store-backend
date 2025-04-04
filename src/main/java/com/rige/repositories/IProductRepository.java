package com.rige.repositories;

import com.rige.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "CALL delete_relationships(:id, :table_name)", nativeQuery = true)
    void deleteRelationship(@Param("id") Long id, @Param("table_name") String tableName);

    @EntityGraph(attributePaths = {"userEntity", "brandEntity", "categoryEntity",
                                "unitMeasureEntity", "lineEntity", "supplierEntity", "imageEntity"})
    Page<ProductEntity> findByFlagAndStatusAndUserEntity_Id(boolean flag,boolean status, Long id,
                                                            Pageable pageable);

    @EntityGraph(attributePaths = {"userEntity", "brandEntity", "categoryEntity",
            "unitMeasureEntity", "lineEntity", "supplierEntity", "imageEntity"})
    Page<ProductEntity> findByFlagAndUserEntity_Id(boolean flag, Long id, Pageable pageable);
}
