package com.einvoice.companyservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.UUID;

@Entity
@IdClass(CompanyEmployeeId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Employment {

    @Id
    private UUID companyId;

    @Id
    private UUID employeeId;

    @Enumerated(EnumType.STRING)
    private Role role;
}
