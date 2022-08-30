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
import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Country {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;
}
