package com.einvoice.companyservice.controller.dto;

import com.einvoice.companyservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetRoleInCompanyResponse {

    private String role;
}
