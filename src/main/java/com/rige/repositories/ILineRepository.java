package com.rige.repositories;

import com.rige.entities.LineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILineRepository extends JpaRepository<LineEntity, Long> {
    List<LineEntity> findByFlag(boolean flag);
}
