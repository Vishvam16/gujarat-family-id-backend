package org.placement.project.service;

import lombok.RequiredArgsConstructor;
import org.placement.project.dto.FamilyMemberRequest;
import org.placement.project.dto.FamilyMemberResponse;
import org.placement.project.entity.Family;
import org.placement.project.entity.FamilyMember;
import org.placement.project.repository.FamilyMemberRepository;
import org.placement.project.repository.FamilyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FamilyMemberService {

    private final FamilyRepository familyRepository;
    private final FamilyMemberRepository familyMemberRepository;

    public FamilyMemberResponse addMember(
            String familyId,
            FamilyMemberRequest request) {

        Family family = familyRepository.findByFamilyId(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found with ID: " + familyId));

        FamilyMember member = FamilyMember.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .identityNumber(request.getIdentityNumber())
                .mobile(request.getMobile())
                .relationship(request.getRelationship())
                .educationLevel(request.getEducationLevel())
                .occupation(request.getOccupation())
                .employmentStatus(request.getEmploymentStatus())
                .annualIncome(request.getAnnualIncome())
                .verified(false)
                .family(family)
                .build();

        return mapToResponse(familyMemberRepository.save(member));
    }

    public List<FamilyMemberResponse> getMembers(String familyId) {

        Family family = familyRepository.findByFamilyId(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found with ID: " + familyId));

        return familyMemberRepository
                .findByFamily_Id(family.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public FamilyMemberResponse getMember(
            String familyId,
            Long memberId) {

        Family family = familyRepository.findByFamilyId(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found with ID: " + familyId));

        FamilyMember member = familyMemberRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("Member not found with ID: " + memberId));

        if (!member.getFamily().getId().equals(family.getId())) {
            throw new RuntimeException("Member does not belong to this family");
        }

        return mapToResponse(member);
    }

    public FamilyMemberResponse updateMember(
            String familyId,
            Long memberId,
            FamilyMemberRequest request) {

        Family family = familyRepository.findByFamilyId(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found with ID: " + familyId));

        FamilyMember member = familyMemberRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("Member not found with ID: " + memberId));

        if (!member.getFamily().getId().equals(family.getId())) {
            throw new RuntimeException("Member does not belong to this family");
        }

        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setDateOfBirth(request.getDateOfBirth());
        member.setGender(request.getGender());
        member.setIdentityNumber(request.getIdentityNumber());
        member.setMobile(request.getMobile());
        member.setRelationship(request.getRelationship());
        member.setEducationLevel(request.getEducationLevel());
        member.setOccupation(request.getOccupation());
        member.setEmploymentStatus(request.getEmploymentStatus());
        member.setAnnualIncome(request.getAnnualIncome());

        return mapToResponse(familyMemberRepository.save(member));
    }

    public void deleteMember(
            String familyId,
            Long memberId) {

        Family family = familyRepository.findByFamilyId(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found with ID: " + familyId));

        FamilyMember member = familyMemberRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("Member not found with ID: " + memberId));

        if (!member.getFamily().getId().equals(family.getId())) {
            throw new RuntimeException("Member does not belong to this family");
        }

        familyMemberRepository.delete(member);
    }

    private FamilyMemberResponse mapToResponse(FamilyMember member) {

        return FamilyMemberResponse.builder()
                .id(member.getId())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .dateOfBirth(member.getDateOfBirth())
                .gender(member.getGender())
                .identityNumber(member.getIdentityNumber())
                .mobile(member.getMobile())
                .relationship(member.getRelationship())
                .educationLevel(member.getEducationLevel())
                .occupation(member.getOccupation())
                .employmentStatus(member.getEmploymentStatus())
                .annualIncome(member.getAnnualIncome())
                .verified(member.getVerified())
                .build();
    }
}