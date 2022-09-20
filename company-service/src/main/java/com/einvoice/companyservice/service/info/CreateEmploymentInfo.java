package com.einvoice.companyservice.service.info;

import com.einvoice.companyservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateEmploymentInfo {

    private UUID companyId;
    private UUID userId;
    private Role role;
}
