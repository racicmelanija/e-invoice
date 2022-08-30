package com.einvoice.companyservice.converter;

import com.einvoice.companyservice.controller.dto.RegisterCompanyRequest;
import com.einvoice.companyservice.service.info.RegisterCompanyInfo;
import com.einvoice.companyservice.service.info.AddressInfo;

public class CompanyConverter {

    public static RegisterCompanyInfo toRegisterCompanyInfo(RegisterCompanyRequest request) {
        return RegisterCompanyInfo.builder()
                .companyName(request.getCompanyName())
                .taxIdentificationNumber(request.getTaxIdentificationNumber())
                .companyRegistrationNumber(request.getCompanyRegistrationNumber())
                .phoneNumber(request.getPhoneNumber())
                .addressInfo(AddressInfo.builder()
                        .address(request.getAddress())
                        .cityId(request.getCityId())
                        .build())
                .build();
    }
}
