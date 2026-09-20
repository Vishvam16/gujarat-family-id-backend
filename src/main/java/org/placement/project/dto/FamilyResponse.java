package org.placement.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyResponse {

    private String familyId;
    private String familyName;
    private Double annualIncome;
    private String category;
    private String ruralUrban;
    private AddressDTO address;
}