package org.placement.project.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyDashboardResponse {

    private FamilyResponse family;

    private Integer totalMembers;

    private List<FamilyMemberResponse> members;

    private List<EligibleSchemeResponse> eligibleSchemes;

    private List<SchemeApplicationResponse> applications;
}