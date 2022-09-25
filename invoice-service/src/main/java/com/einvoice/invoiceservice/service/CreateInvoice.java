package com.einvoice.invoiceservice.service;

import com.einvoice.invoiceservice.model.Invoice;
import com.einvoice.invoiceservice.model.InvoiceItem;
import com.einvoice.invoiceservice.repository.InvoiceItemRepository;
import com.einvoice.invoiceservice.repository.InvoiceRepository;
import com.einvoice.invoiceservice.service.info.CreateInvoiceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.einvoice.invoiceservice.converter.InvoiceConverter.toInvoice;
import static com.einvoice.invoiceservice.converter.InvoiceConverter.toInvoiceItemList;
import static com.einvoice.invoiceservice.model.InvoiceStatus.SENT;

@Service
@RequiredArgsConstructor
public class CreateInvoice {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemRepository invoiceItemRepository;

    @Transactional(rollbackFor = Throwable.class)
    public void execute(CreateInvoiceInfo info) {
        Invoice invoice = toInvoice(info);
        invoice.setStatus(SENT);
        List<InvoiceItem> items = toInvoiceItemList(info.getInvoiceItemsInfo(), invoice);

        invoiceRepository.save(invoice);
        invoiceItemRepository.saveAll(items);
    }
}
