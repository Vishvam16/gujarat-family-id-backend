package org.placement.project.service;

import lombok.RequiredArgsConstructor;
import org.placement.project.dto.AddressDTO;
import org.placement.project.dto.CreateFamilyRequest;
import org.placement.project.dto.FamilyResponse;
import org.placement.project.entity.Address;
import org.placement.project.entity.Family;
import org.placement.project.repository.FamilyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;

    public FamilyResponse createFamily(CreateFamilyRequest request) {

        String familyId = generateFamilyId();

        Address address = Address.builder()
                .houseNumber(request.getAddress().getHouseNumber())
                .street(request.getAddress().getStreet())
                .village(request.getAddress().getVillage())
                .city(request.getAddress().getCity())
                .district(request.getAddress().getDistrict())
                .state(request.getAddress().getState())
                .pincode(request.getAddress().getPincode())
                .build();

        Family family = Family.builder()
                .familyId(familyId)
                .familyName(request.getFamilyName())
                .annualIncome(request.getAnnualIncome())
                .category(request.getCategory())
                .ruralUrban(request.getRuralUrban())
                .address(address)
                .build();

        Family savedFamily = familyRepository.save(family);

        return mapToResponse(savedFamily);
    }

    public FamilyResponse getFamily(String familyId) {

        Family family = familyRepository.findByFamilyId(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found with ID: " + familyId));

        return mapToResponse(family);
    }

    private String generateFamilyId() {

        return "GJ-FAM-" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 10)
                        .toUpperCase();
    }

    private FamilyResponse mapToResponse(Family family) {

        Address address = family.getAddress();

        AddressDTO addressDTO = AddressDTO.builder()
                .houseNumber(address.getHouseNumber())
                .street(address.getStreet())
                .village(address.getVillage())
                .city(address.getCity())
                .district(address.getDistrict())
                .state(address.getState())
                .pincode(address.getPincode())
                .build();

        return FamilyResponse.builder()
                .familyId(family.getFamilyId())
                .familyName(family.getFamilyName())
                .annualIncome(family.getAnnualIncome())
                .category(family.getCategory())
                .ruralUrban(family.getRuralUrban())
                .address(addressDTO)
                .build();
    }
}