package com.einvoice.companyservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterCompanyRequest {

    @NotBlank(message = "Company name can't be empty")
    private String companyName;

    @Size(min = 9, max = 9, message = "Tax identification number must contain 9 digits")
    private String taxIdentificationNumber;

    @Size(min = 8, max = 8, message = "Company registration number must contain 8 digits")
    private String companyRegistrationNumber;

    @NotBlank(message = "Phone number can't be empty")
    private String phoneNumber;

    @NotBlank(message = "Address can't be empty")
    private String address;

    @NotNull
    private UUID cityId;
}
