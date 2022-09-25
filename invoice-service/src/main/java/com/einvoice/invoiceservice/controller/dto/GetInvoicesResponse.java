package com.einvoice.invoiceservice.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetInvoicesResponse {

    private List<GetInvoiceResponse> invoices;
    private long totalElements;
}
