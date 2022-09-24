package com.einvoice.invoiceservice.service.info;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class CreateInvoiceInfo {

    private String companyTaxId;
    private String clientTaxId;
    private String bankAccount;
    private String referenceNumber;
    private List<InvoiceItemInfo> invoiceItemsInfo;
}
