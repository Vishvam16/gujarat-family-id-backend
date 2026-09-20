package org.placement.project.repository;

import org.placement.project.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family, Long> {

    Optional<Family> findByFamilyId(String familyId);

    boolean existsByFamilyId(String familyId);
}