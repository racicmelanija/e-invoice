package com.einvoice.companyservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Company {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String companyName;

    @Column(unique = true)
    private String taxIdentificationNumber;

    @Column(unique = true)
    private String companyRegistrationNumber;

    private String phoneNumber;

    @ManyToOne
    private Address address;
}
