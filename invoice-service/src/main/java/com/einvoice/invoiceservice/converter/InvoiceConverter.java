package com.einvoice.invoiceservice.converter;

import com.einvoice.invoiceservice.controller.dto.CreateInvoiceRequest;
import com.einvoice.invoiceservice.controller.dto.InvoiceItemRequest;
import com.einvoice.invoiceservice.model.Invoice;
import com.einvoice.invoiceservice.model.InvoiceItem;
import com.einvoice.invoiceservice.service.info.CreateInvoiceInfo;
import com.einvoice.invoiceservice.service.info.InvoiceItemInfo;

import java.util.List;
import java.util.stream.Collectors;

public class InvoiceConverter {

    public static CreateInvoiceInfo toCreateInvoiceInfo(CreateInvoiceRequest request) {
        return CreateInvoiceInfo.builder()
                .companyTaxId(request.getCompanyTaxId())
                .clientTaxId(request.getClientTaxId())
                .bankAccount(request.getBankAccount())
                .referenceNumber(request.getReferenceNumber())
                .invoiceItemsInfo(toInvoiceItemInfoList(request.getInvoiceItems()))
                .build();
    }

    public static List<InvoiceItemInfo> toInvoiceItemInfoList(List<InvoiceItemRequest> requestList) {
        return requestList.stream()
                .map(request -> InvoiceItemInfo.builder()
                        .name(request.getName())
                        .unitOfMeasure(request.getUnitOfMeasure())
                        .unitPrice(request.getUnitPrice())
                        .quantity(request.getQuantity())
                        .discount(request.getDiscount())
                        .taxPercent(request.getTaxPercent())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static Invoice toInvoice(CreateInvoiceInfo info) {
        return Invoice.builder()
                .companyTaxId(info.getCompanyTaxId())
                .clientTaxId(info.getClientTaxId())
                .bankAccount(info.getBankAccount())
                .referenceNumber(info.getReferenceNumber())
                .build();
    }

    public static List<InvoiceItem> toInvoiceItemList(List<InvoiceItemInfo> infoList, Invoice invoice) {
        return infoList.stream()
                .map(info -> InvoiceItem.builder()
                        .name(info.getName())
                        .unitOfMeasure(info.getUnitOfMeasure())
                        .unitPrice(info.getUnitPrice())
                        .quantity(info.getQuantity())
                        .discount(info.getDiscount())
                        .taxPercent(info.getTaxPercent())
                        .invoice(invoice)
                        .build()
                )
                .collect(Collectors.toList());
    }
}
