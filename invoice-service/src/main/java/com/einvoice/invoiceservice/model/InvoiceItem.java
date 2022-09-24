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
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InvoiceItem {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String unitOfMeasure;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Double taxPercent;

    @ManyToOne
    private Invoice invoice;
}
