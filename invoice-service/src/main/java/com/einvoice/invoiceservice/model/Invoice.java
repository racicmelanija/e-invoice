package com.einvoice.invoiceservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Invoice {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String companyTaxId;

    @Column(nullable = false)
    private String clientTaxId;

    @Column(nullable = false)
    private String bankAccount;

    @Column(nullable = false)
    private String referenceNumber;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date sentAt;

    @OneToMany
    private List<InvoiceItem> items;
}
