package com.einvoice.companyservice.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCompanyResponse {

    private String taxId;
    private String bankAccount;
}
