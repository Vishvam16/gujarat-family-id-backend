package org.placement.project.controller;

import lombok.RequiredArgsConstructor;
import org.placement.project.dto.EligibleSchemeResponse;
import org.placement.project.service.EligibilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families")
@RequiredArgsConstructor
public class EligibilityController {

    private final EligibilityService eligibilityService;

    @GetMapping("/{familyId}/eligible-schemes")
    public ResponseEntity<List<EligibleSchemeResponse>> getEligibleSchemes(
            @PathVariable String familyId) {

        return ResponseEntity.ok(
                eligibilityService.getEligibleSchemes(familyId)
        );
    }
}