package org.placement.project.service;

import lombok.RequiredArgsConstructor;
import org.placement.project.dto.SchemeApplicationRequest;
import org.placement.project.dto.SchemeApplicationResponse;
import org.placement.project.entity.ApplicationStatus;
import org.placement.project.entity.Family;
import org.placement.project.entity.Scheme;
import org.placement.project.entity.SchemeApplication;
import org.placement.project.repository.FamilyRepository;
import org.placement.project.repository.SchemeApplicationRepository;
import org.placement.project.repository.SchemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchemeApplicationService {

    private final FamilyRepository familyRepository;
    private final SchemeRepository schemeRepository;
    private final SchemeApplicationRepository schemeApplicationRepository;
    private final EligibilityService eligibilityService;

    public SchemeApplicationResponse applyForScheme(
            String familyId,
            SchemeApplicationRequest request) {

        Family family = familyRepository.findByFamilyId(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found with ID: " + familyId));

        Scheme scheme = schemeRepository.findById(request.getSchemeId())
                .orElseThrow(() ->
                        new RuntimeException("Scheme not found with ID: " + request.getSchemeId()));

        if (!scheme.getActive()) {
            throw new RuntimeException("Scheme is currently inactive");
        }

        if (schemeApplicationRepository
                .existsByFamily_IdAndScheme_Id(family.getId(), scheme.getId())) {
            throw new RuntimeException(
                    "Family has already applied for this scheme"
            );
        }

        boolean eligible = eligibilityService
                .getEligibleSchemes(familyId)
                .stream()
                .anyMatch(e -> e.getSchemeId().equals(scheme.getId()));

        if (!eligible) {
            throw new RuntimeException(
                    "Family is not eligible for this scheme"
            );
        }

        SchemeApplication application = SchemeApplication.builder()
                .family(family)
                .scheme(scheme)
                .status(ApplicationStatus.APPLIED)
                .build();

        return mapToResponse(
                schemeApplicationRepository.save(application)
        );
    }

    public List<SchemeApplicationResponse> getApplications(
            String familyId) {

        Family family = familyRepository.findByFamilyId(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found with ID: " + familyId));

        return schemeApplicationRepository
                .findByFamily_Id(family.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SchemeApplicationResponse getApplication(
            String familyId,
            Long applicationId) {

        Family family = familyRepository.findByFamilyId(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found with ID: " + familyId));

        SchemeApplication application =
                schemeApplicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found with ID: " + applicationId
                                ));

        validateOwnership(family, application);

        return mapToResponse(application);
    }

    public SchemeApplicationResponse updateStatus(
            String familyId,
            Long applicationId,
            ApplicationStatus status) {

        Family family = familyRepository.findByFamilyId(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found with ID: " + familyId));

        SchemeApplication application =
                schemeApplicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found with ID: " + applicationId
                                ));

        validateOwnership(family, application);

        application.setStatus(status);

        return mapToResponse(
                schemeApplicationRepository.save(application)
        );
    }

    private void validateOwnership(
            Family family,
            SchemeApplication application) {

        if (!application.getFamily().getId().equals(family.getId())) {
            throw new RuntimeException(
                    "Application does not belong to this family"
            );
        }
    }

    private SchemeApplicationResponse mapToResponse(
            SchemeApplication application) {

        return SchemeApplicationResponse.builder()
                .applicationId(application.getId())
                .schemeId(application.getScheme().getId())
                .schemeName(application.getScheme().getName())
                .schemeCategory(application.getScheme().getCategory())
                .status(application.getStatus().name())
                .appliedAt(application.getAppliedAt())
                .updatedAt(application.getUpdatedAt())
                .build();
    }
}