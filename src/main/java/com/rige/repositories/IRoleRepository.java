package com.rige.repositories;

import com.rige.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    boolean existsByName(String name);
    Optional<RoleEntity> findByName(String name);
}
