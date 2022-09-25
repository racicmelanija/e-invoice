package com.einvoice.invoiceservice.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InvoiceItemResponse {

    private String name;
    private String unitOfMeasure;
    private Double unitPrice;
    private Double quantity;
    private Double discount;
    private Double taxPercent;
}
