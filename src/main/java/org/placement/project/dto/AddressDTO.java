package org.placement.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    private String houseNumber;
    private String street;
    private String village;
    private String city;
    private String district;
    private String state;
    private String pincode;
}