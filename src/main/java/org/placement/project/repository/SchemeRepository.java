package org.placement.project.repository;

import org.placement.project.entity.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchemeRepository extends JpaRepository<Scheme, Long> {

    boolean existsByName(String name);
}