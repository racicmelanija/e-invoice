package com.einvoice.companyservice.controller.dto;

import com.einvoice.companyservice.model.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCompaniesResponse {

    private List<Company> companies;
    private long totalElements;
}
