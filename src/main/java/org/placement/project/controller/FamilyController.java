package org.placement.project.controller;

import lombok.RequiredArgsConstructor;
import org.placement.project.dto.CreateFamilyRequest;
import org.placement.project.dto.FamilyResponse;
import org.placement.project.service.FamilyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/families")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping
    public ResponseEntity<FamilyResponse> createFamily(
            @RequestBody CreateFamilyRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(familyService.createFamily(request));
    }

    @GetMapping("/{familyId}")
    public ResponseEntity<FamilyResponse> getFamily(
            @PathVariable String familyId) {

        return ResponseEntity.ok(
                familyService.getFamily(familyId)
        );
    }
}