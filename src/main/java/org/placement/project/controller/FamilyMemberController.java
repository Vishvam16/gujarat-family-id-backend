package org.placement.project.controller;

import lombok.RequiredArgsConstructor;
import org.placement.project.dto.FamilyMemberRequest;
import org.placement.project.dto.FamilyMemberResponse;
import org.placement.project.service.FamilyMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families/{familyId}/members")
@RequiredArgsConstructor
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;

    @PostMapping
    public ResponseEntity<FamilyMemberResponse> addMember(
            @PathVariable String familyId,
            @RequestBody FamilyMemberRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(familyMemberService.addMember(familyId, request));
    }

    @GetMapping
    public ResponseEntity<List<FamilyMemberResponse>> getMembers(
            @PathVariable String familyId) {

        return ResponseEntity.ok(
                familyMemberService.getMembers(familyId)
        );
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<FamilyMemberResponse> getMember(
            @PathVariable String familyId,
            @PathVariable Long memberId) {

        return ResponseEntity.ok(
                familyMemberService.getMember(familyId, memberId)
        );
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<FamilyMemberResponse> updateMember(
            @PathVariable String familyId,
            @PathVariable Long memberId,
            @RequestBody FamilyMemberRequest request) {

        return ResponseEntity.ok(
                familyMemberService.updateMember(
                        familyId,
                        memberId,
                        request
                )
        );
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(
            @PathVariable String familyId,
            @PathVariable Long memberId) {

        familyMemberService.deleteMember(familyId, memberId);

        return ResponseEntity.noContent().build();
    }
}