package com.einvoice.companyservice.service.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterCompanyInfo {

    private String companyName;
    private String taxIdentificationNumber;
    private String companyRegistrationNumber;
    private String phoneNumber;
    private AddressInfo addressInfo;
}
