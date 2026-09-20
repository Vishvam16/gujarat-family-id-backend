package org.placement.project.dto;

import lombok.*;
import org.placement.project.entity.ApplicationStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStatusRequest {

    private ApplicationStatus status;
}