package com.einvoice.invoiceservice.service;

import com.einvoice.invoiceservice.exception.InvoiceNotFoundException;
import com.einvoice.invoiceservice.model.Invoice;
import com.einvoice.invoiceservice.model.InvoiceStatus;
import com.einvoice.invoiceservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateInvoiceStatus {

    private final InvoiceRepository invoiceRepository;

    @Transactional(rollbackFor = Throwable.class)
    public void execute(UUID invoiceId, InvoiceStatus invoiceStatus) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(InvoiceNotFoundException::new);
        invoice.setStatus(invoiceStatus);
    }
}
