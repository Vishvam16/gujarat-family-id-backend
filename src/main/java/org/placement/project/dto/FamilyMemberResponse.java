package org.placement.project.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyMemberResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String identityNumber;
    private String mobile;
    private String relationship;
    private String educationLevel;
    private String occupation;
    private String employmentStatus;
    private Double annualIncome;
    private Boolean verified;
}