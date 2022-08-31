package com.einvoice.companyservice.converter;

import com.einvoice.companyservice.controller.dto.RegisterEmployeeRequest;
import com.einvoice.companyservice.service.info.RegisterEmployeeInfo;

import java.util.UUID;

public class EmployeeConverter {

    public static RegisterEmployeeInfo toRegisterEmployeeInfo(RegisterEmployeeRequest request, UUID adminId) {
        return RegisterEmployeeInfo.builder()
                .adminId(adminId)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .role(request.getRole())
                .companyId(request.getCompanyId())
                .build();
    }
}
