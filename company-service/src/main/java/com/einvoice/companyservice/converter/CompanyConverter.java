package com.einvoice.companyservice.converter;

import com.einvoice.companyservice.controller.dto.RegisterCompanyRequest;
import com.einvoice.companyservice.service.info.ForeignCurrencyBankAccountInfo;
import com.einvoice.companyservice.service.info.LocalCurrencyBankAccountInfo;
import com.einvoice.companyservice.service.info.RegisterCompanyInfo;
import com.einvoice.companyservice.service.info.AddressInfo;

import java.util.UUID;

public class CompanyConverter {

    public static RegisterCompanyInfo toRegisterCompanyInfo(RegisterCompanyRequest request, UUID companyOwnerId) {
        return RegisterCompanyInfo.builder()
                .companyOwnerId(companyOwnerId)
                .companyName(request.getCompanyName())
                .taxIdentificationNumber(request.getTaxIdentificationNumber())
                .companyRegistrationNumber(request.getCompanyRegistrationNumber())
                .phoneNumber(request.getPhoneNumber())
                .addressInfo(AddressInfo.builder()
                        .address(request.getAddress())
                        .cityId(request.getCityId())
                        .build())
                .localCurrencyBankAccountInfo(!request.getLocalCurrencyBankAccount().isBlank() ? LocalCurrencyBankAccountInfo.builder()
                        .accountNumber(request.getLocalCurrencyBankAccount())
                        .eBankingFormat(request.getBankingFormat())
                        .build() : null)
                .foreignCurrencyBankAccountInfo(!request.getForeignCurrencyBankAccount().isBlank() ? ForeignCurrencyBankAccountInfo.builder()
                        .accountNumber(request.getForeignCurrencyBankAccount())
                        .currency(request.getCurrency())
                        .build() : null)
                .build();
    }
}
