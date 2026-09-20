package org.placement.project.repository;

import org.placement.project.entity.SchemeApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchemeApplicationRepository
        extends JpaRepository<SchemeApplication, Long> {

    List<SchemeApplication> findByFamily_Id(Long familyId);

    Optional<SchemeApplication> findByFamily_IdAndScheme_Id(
            Long familyId,
            Long schemeId
    );

    boolean existsByFamily_IdAndScheme_Id(
            Long familyId,
            Long schemeId
    );
}