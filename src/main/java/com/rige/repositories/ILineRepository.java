package com.rige.repositories;

import com.rige.entities.LineEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILineRepository extends JpaRepository<LineEntity, Long> {
    @EntityGraph("userEntity")
    List<LineEntity> findByFlagAndUserEntity_Id(boolean flag, Long id);

    @EntityGraph("userEntity")
    List<LineEntity> findByFlagAndStatusAndUserEntity_Id(boolean flag, boolean status, Long id);
}
