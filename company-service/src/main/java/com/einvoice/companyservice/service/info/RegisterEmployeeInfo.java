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
public class RegisterEmployeeInfo {

    private UUID adminId;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private UUID companyId;
}
