package com.einvoice.invoiceservice.controller.dto;

import com.einvoice.invoiceservice.model.InvoiceItem;
import com.einvoice.invoiceservice.model.InvoiceStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class GetInvoiceResponse {

    private UUID invoiceId;
    private String companyTaxId;
    private String clientTaxId;
    private String bankAccount;
    private String referenceNumber;
    private InvoiceStatus status;
    private List<InvoiceItemResponse> invoiceItems;
    private Double total;
}
