package org.placement.project.controller;

import lombok.RequiredArgsConstructor;
import org.placement.project.dto.ApplicationStatusRequest;
import org.placement.project.dto.SchemeApplicationRequest;
import org.placement.project.dto.SchemeApplicationResponse;
import org.placement.project.service.SchemeApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families/{familyId}/applications")
@RequiredArgsConstructor
public class SchemeApplicationController {

    private final SchemeApplicationService schemeApplicationService;

    @PostMapping
    public ResponseEntity<SchemeApplicationResponse> applyForScheme(
            @PathVariable String familyId,
            @RequestBody SchemeApplicationRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        schemeApplicationService.applyForScheme(
                                familyId,
                                request
                        )
                );
    }

    @GetMapping
    public ResponseEntity<List<SchemeApplicationResponse>> getApplications(
            @PathVariable String familyId) {

        return ResponseEntity.ok(
                schemeApplicationService.getApplications(familyId)
        );
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<SchemeApplicationResponse> getApplication(
            @PathVariable String familyId,
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                schemeApplicationService.getApplication(
                        familyId,
                        applicationId
                )
        );
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<SchemeApplicationResponse> updateStatus(
            @PathVariable String familyId,
            @PathVariable Long applicationId,
            @RequestBody ApplicationStatusRequest request) {

        return ResponseEntity.ok(
                schemeApplicationService.updateStatus(
                        familyId,
                        applicationId,
                        request.getStatus()
                )
        );
    }
}