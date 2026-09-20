package org.placement.project.service;

import lombok.RequiredArgsConstructor;
import org.placement.project.entity.Scheme;
import org.placement.project.repository.SchemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchemeService {

    private final SchemeRepository schemeRepository;

    public Scheme createScheme(Scheme scheme) {

        if (schemeRepository.existsByName(scheme.getName())) {
            throw new RuntimeException("Scheme already exists with name: " + scheme.getName());
        }

        return schemeRepository.save(scheme);
    }

    public List<Scheme> getAllSchemes() {
        return schemeRepository.findAll();
    }

    public Scheme getScheme(Long schemeId) {

        return schemeRepository.findById(schemeId)
                .orElseThrow(() ->
                        new RuntimeException("Scheme not found with ID: " + schemeId));
    }

    public Scheme updateScheme(Long schemeId, Scheme updatedScheme) {

        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() ->
                        new RuntimeException("Scheme not found with ID: " + schemeId));

        scheme.setName(updatedScheme.getName());
        scheme.setDescription(updatedScheme.getDescription());
        scheme.setCategory(updatedScheme.getCategory());
        scheme.setMinimumIncome(updatedScheme.getMinimumIncome());
        scheme.setMaximumIncome(updatedScheme.getMaximumIncome());
        scheme.setRuralUrban(updatedScheme.getRuralUrban());
        scheme.setRequiredCategory(updatedScheme.getRequiredCategory());
        scheme.setActive(updatedScheme.getActive());

        return schemeRepository.save(scheme);
    }

    public void deleteScheme(Long schemeId) {

        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() ->
                        new RuntimeException("Scheme not found with ID: " + schemeId));

        schemeRepository.delete(scheme);
    }
}