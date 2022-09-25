package com.einvoice.invoiceservice.service.info;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InvoiceItemInfo {

    private String name;
    private String unitOfMeasure;
    private Double unitPrice;
    private Double quantity;
    private Double discount;
    private Double taxPercent;
}
