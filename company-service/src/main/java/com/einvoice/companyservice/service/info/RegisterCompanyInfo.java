package com.einvoice.companyservice.service.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterCompanyInfo {

    private UUID companyOwnerId;
    private String companyName;
    private String taxIdentificationNumber;
    private String companyRegistrationNumber;
    private String phoneNumber;
    private AddressInfo addressInfo;
    private LocalCurrencyBankAccountInfo localCurrencyBankAccountInfo;
    private ForeignCurrencyBankAccountInfo foreignCurrencyBankAccountInfo;
}
