package com.einvoice.companyservice.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CompanyClientId implements Serializable {

    private UUID companyId;
    private UUID clientId;
}
