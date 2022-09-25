package com.einvoice.companyservice.converter;

import com.einvoice.companyservice.controller.dto.GetCompaniesResponse;
import com.einvoice.companyservice.controller.dto.GetCompanyResponse;
import com.einvoice.companyservice.controller.dto.RegisterCompanyRequest;
import com.einvoice.companyservice.model.Company;
import com.einvoice.companyservice.service.info.ForeignCurrencyBankAccountInfo;
import com.einvoice.companyservice.service.info.GetCompaniesInfo;
import com.einvoice.companyservice.service.info.LocalCurrencyBankAccountInfo;
import com.einvoice.companyservice.service.info.RegisterCompanyInfo;
import com.einvoice.companyservice.service.info.AddressInfo;
import com.einvoice.companyservice.service.result.GetCompanyByIdResult;
import org.springframework.data.domain.Page;

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

    public static GetCompaniesResponse toGetCompaniesResponse(Page<Company> result) {
        return GetCompaniesResponse.builder()
                .companies(result.getContent())
                .totalElements(result.getTotalElements())
                .build();
    }

    public static GetCompaniesInfo toGetCompaniesInfo(UUID notClientsWith, String search, int page, int size) {
        return GetCompaniesInfo.builder()
                .notClientsWith(notClientsWith)
                .search(search)
                .page(page)
                .size(size)
                .build();
    }

    public static GetCompanyResponse toGetCompanyResponse(GetCompanyByIdResult result) {
        return GetCompanyResponse.builder()
                .taxId(result.getTaxId())
                .bankAccount(result.getBankAccount())
                .build();
    }
}
