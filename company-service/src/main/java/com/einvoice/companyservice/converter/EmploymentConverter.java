package com.einvoice.companyservice.converter;

import com.einvoice.companyservice.controller.dto.CreateEmploymentRequest;
import com.einvoice.companyservice.controller.dto.GetEmploymentsResponse;
import com.einvoice.companyservice.service.info.CreateEmploymentInfo;
import com.einvoice.companyservice.service.result.GetEmploymentsResult;

import java.util.List;

public class EmploymentConverter {

    public static GetEmploymentsResponse toGetEmploymentsResponse(List<GetEmploymentsResult> result) {
        return GetEmploymentsResponse.builder()
                .employments(result)
                .build();
    }

    public static CreateEmploymentInfo toCreateEmploymentInfo(CreateEmploymentRequest request) {
        return CreateEmploymentInfo.builder()
                .companyId(request.getCompanyId())
                .userId(request.getUserId())
                .role(request.getRole())
                .build();
    }
}
