package com.einvoice.invoiceservice.service.info;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
public class CreateInvoiceInfo {

    private UUID companyId;
    private String companyTaxId;
    private UUID clientId;
    private String clientTaxId;
    private String bankAccount;
    private String referenceNumber;
    private List<InvoiceItemInfo> invoiceItemsInfo;
    private Double total;
}
