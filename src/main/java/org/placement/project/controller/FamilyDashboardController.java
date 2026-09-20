package org.placement.project.controller;

import lombok.RequiredArgsConstructor;
import org.placement.project.dto.FamilyDashboardResponse;
import org.placement.project.service.FamilyDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/families")
@RequiredArgsConstructor
public class FamilyDashboardController {

    private final FamilyDashboardService familyDashboardService;

    @GetMapping("/{familyId}/dashboard")
    public ResponseEntity<FamilyDashboardResponse> getDashboard(
            @PathVariable String familyId) {

        return ResponseEntity.ok(
                familyDashboardService.getDashboard(familyId)
        );
    }
}