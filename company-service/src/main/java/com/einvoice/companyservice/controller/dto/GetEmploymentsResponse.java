package com.einvoice.companyservice.controller.dto;

import com.einvoice.companyservice.model.Role;
import com.einvoice.companyservice.service.result.GetEmploymentsResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetEmploymentsResponse {

    private List<GetEmploymentsResult> employments;
}
