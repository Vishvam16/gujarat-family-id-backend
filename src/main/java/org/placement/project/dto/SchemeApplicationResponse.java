package org.placement.project.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchemeApplicationResponse {

    private Long applicationId;
    private Long schemeId;
    private String schemeName;
    private String schemeCategory;
    private String status;
    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;
}