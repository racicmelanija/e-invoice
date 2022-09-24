package com.einvoice.invoiceservice.controller;

import com.einvoice.invoiceservice.controller.dto.CreateInvoiceRequest;
import com.einvoice.invoiceservice.service.CreateInvoice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.einvoice.invoiceservice.converter.InvoiceConverter.toCreateInvoiceInfo;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final CreateInvoice createInvoice;

    @PostMapping
    public void createInvoice(@Valid @RequestBody CreateInvoiceRequest request) {
        createInvoice.execute(toCreateInvoiceInfo(request));
    }
}
