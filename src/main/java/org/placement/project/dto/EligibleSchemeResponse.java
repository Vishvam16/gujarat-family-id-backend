package org.placement.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EligibleSchemeResponse {

    private Long schemeId;
    private String name;
    private String description;
    private String category;
    private String eligibilityReason;
}