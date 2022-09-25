package com.einvoice.invoiceservice.service;

import com.einvoice.invoiceservice.model.Invoice;
import com.einvoice.invoiceservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetOutgoingInvoices {

    private final InvoiceRepository invoiceRepository;

    @Transactional(readOnly = true)
    public Page<Invoice> execute(UUID companyId, int page, int size) {
        return invoiceRepository.findAllByCompanyId(companyId, PageRequest.of(page, size));
    }
}
