package org.placement.project.service;

import lombok.RequiredArgsConstructor;
import org.placement.project.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FamilyDashboardService {

    private final FamilyService familyService;
    private final FamilyMemberService familyMemberService;
    private final EligibilityService eligibilityService;
    private final SchemeApplicationService schemeApplicationService;

    public FamilyDashboardResponse getDashboard(String familyId) {

        FamilyResponse family =
                familyService.getFamily(familyId);

        List<FamilyMemberResponse> members =
                familyMemberService.getMembers(familyId);

        List<EligibleSchemeResponse> eligibleSchemes =
                eligibilityService.getEligibleSchemes(familyId);

        List<SchemeApplicationResponse> applications =
                schemeApplicationService.getApplications(familyId);

        return FamilyDashboardResponse.builder()
                .family(family)
                .totalMembers(members.size())
                .members(members)
                .eligibleSchemes(eligibleSchemes)
                .applications(applications)
                .build();
    }
}