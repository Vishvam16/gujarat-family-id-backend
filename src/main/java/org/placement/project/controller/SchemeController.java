package org.placement.project.controller;

import lombok.RequiredArgsConstructor;
import org.placement.project.entity.Scheme;
import org.placement.project.service.SchemeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schemes")
@RequiredArgsConstructor
public class SchemeController {

    private final SchemeService schemeService;

    @PostMapping
    public ResponseEntity<Scheme> createScheme(
            @RequestBody Scheme scheme) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(schemeService.createScheme(scheme));
    }

    @GetMapping
    public ResponseEntity<List<Scheme>> getAllSchemes() {

        return ResponseEntity.ok(
                schemeService.getAllSchemes()
        );
    }

    @GetMapping("/{schemeId}")
    public ResponseEntity<Scheme> getScheme(
            @PathVariable Long schemeId) {

        return ResponseEntity.ok(
                schemeService.getScheme(schemeId)
        );
    }

    @PutMapping("/{schemeId}")
    public ResponseEntity<Scheme> updateScheme(
            @PathVariable Long schemeId,
            @RequestBody Scheme scheme) {

        return ResponseEntity.ok(
                schemeService.updateScheme(schemeId, scheme)
        );
    }

    @DeleteMapping("/{schemeId}")
    public ResponseEntity<Void> deleteScheme(
            @PathVariable Long schemeId) {

        schemeService.deleteScheme(schemeId);

        return ResponseEntity.noContent().build();
    }
}