package com.einvoice.companyservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.UUID;

@Entity
@IdClass(CompanyClientId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Collaboration {

    @Id
    private UUID companyId;

    @Id
    private UUID clientId;
}
