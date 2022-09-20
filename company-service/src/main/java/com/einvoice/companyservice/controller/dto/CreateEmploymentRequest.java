package com.einvoice.companyservice.controller.dto;

import com.einvoice.companyservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateEmploymentRequest {

    @NotNull
    private UUID companyId;

    @NotNull
    private UUID userId;

    @NotNull
    private Role role;
}
