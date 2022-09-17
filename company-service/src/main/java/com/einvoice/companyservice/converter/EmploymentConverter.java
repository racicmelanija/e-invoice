package com.einvoice.companyservice.converter;

import com.einvoice.companyservice.controller.dto.GetEmploymentsResponse;
import com.einvoice.companyservice.service.result.GetEmploymentsResult;

import java.util.List;

public class EmploymentConverter {

    public static GetEmploymentsResponse toGetEmploymentsResponse(List<GetEmploymentsResult> result) {
        return GetEmploymentsResponse.builder()
                .employments(result)
                .build();
    }
}
