package com.einvoice.invoiceservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InvoiceItemRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String unitOfMeasure;

    @NotNull
    @Min(0)
    private Double unitPrice;

    @NotNull
    @Min(0)
    private Double quantity;

    @NotNull
    @Min(0)
    private Double discount;

    @NotNull
    @Min(0)
    private Double taxPercent;
}
