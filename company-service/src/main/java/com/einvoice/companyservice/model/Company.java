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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Company name can't be empty")
    private String companyName;

    @Size(min = 9, max = 9, message = "Tax identification number must contain 9 digits")
    @Column(unique = true)
    private String taxIdentificationNumber;

    @Size(min = 8, max = 8, message = "Company registration number must contain 8 digits")
    @Column(unique = true)
    private String companyRegistrationNumber;

    @NotBlank
    private String phoneNumber;

    @ManyToOne
    private Address address;
}
