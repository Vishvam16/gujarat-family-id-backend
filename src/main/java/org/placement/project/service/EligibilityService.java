package org.placement.project.service;

import lombok.RequiredArgsConstructor;
import org.placement.project.dto.EligibleSchemeResponse;
import org.placement.project.entity.Family;
import org.placement.project.entity.Scheme;
import org.placement.project.repository.FamilyRepository;
import org.placement.project.repository.SchemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EligibilityService {

    private final FamilyRepository familyRepository;
    private final SchemeRepository schemeRepository;

    public List<EligibleSchemeResponse> getEligibleSchemes(String familyId) {

        Family family = familyRepository.findByFamilyId(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found with ID: " + familyId));

        return schemeRepository.findAll()
                .stream()
                .filter(Scheme::getActive)
                .filter(scheme -> isEligible(family, scheme))
                .map(scheme -> EligibleSchemeResponse.builder()
                        .schemeId(scheme.getId())
                        .name(scheme.getName())
                        .description(scheme.getDescription())
                        .category(scheme.getCategory())
                        .eligibilityReason(
                                buildEligibilityReason(family, scheme)
                        )
                        .build())
                .toList();
    }

    private boolean isEligible(Family family, Scheme scheme) {

        if (scheme.getMinimumIncome() != null &&
                family.getAnnualIncome() < scheme.getMinimumIncome()) {
            return false;
        }

        if (scheme.getMaximumIncome() != null &&
                family.getAnnualIncome() > scheme.getMaximumIncome()) {
            return false;
        }

        if (scheme.getRuralUrban() != null &&
                !scheme.getRuralUrban().equalsIgnoreCase("BOTH") &&
                !scheme.getRuralUrban().equalsIgnoreCase(family.getRuralUrban())) {
            return false;
        }

        if (scheme.getRequiredCategory() != null &&
                !scheme.getRequiredCategory().equalsIgnoreCase("ALL") &&
                !scheme.getRequiredCategory().equalsIgnoreCase(family.getCategory())) {
            return false;
        }

        return true;
    }

    private String buildEligibilityReason(Family family, Scheme scheme) {

        return "Family meets the income, location and category requirements";
    }
}