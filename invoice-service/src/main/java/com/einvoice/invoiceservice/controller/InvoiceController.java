package com.einvoice.invoiceservice.controller;

import com.einvoice.invoiceservice.controller.dto.CreateInvoiceRequest;
import com.einvoice.invoiceservice.controller.dto.GetInvoicesResponse;
import com.einvoice.invoiceservice.controller.dto.UpdateInvoiceStatusRequest;
import com.einvoice.invoiceservice.service.CreateInvoice;
import com.einvoice.invoiceservice.service.GetIncomingInvoices;
import com.einvoice.invoiceservice.service.GetOutgoingInvoices;
import com.einvoice.invoiceservice.service.UpdateInvoiceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.UUID;

import static com.einvoice.invoiceservice.converter.InvoiceConverter.toCreateInvoiceInfo;
import static com.einvoice.invoiceservice.converter.InvoiceConverter.toGetInvoicesResponse;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final CreateInvoice createInvoice;
    private final GetOutgoingInvoices getOutgoingInvoices;
    private final GetIncomingInvoices getIncomingInvoices;
    private final UpdateInvoiceStatus updateInvoiceStatus;

    @PostMapping
    public void createInvoice(@Valid @RequestBody CreateInvoiceRequest request) {
        createInvoice.execute(toCreateInvoiceInfo(request));
    }

    @GetMapping("/outgoing")
    public GetInvoicesResponse getOutgoingInvoices(@RequestParam UUID companyId, @RequestParam int page, @RequestParam int size) {
        return toGetInvoicesResponse(getOutgoingInvoices.execute(companyId, page, size));
    }

    @GetMapping("/incoming")
    public GetInvoicesResponse getIncomingInvoices(@RequestParam UUID clientId, @RequestParam int page, @RequestParam int size) {
        return toGetInvoicesResponse(getIncomingInvoices.execute(clientId, page, size));
    }

    @PutMapping
    public void updateStatus(@Valid @RequestBody UpdateInvoiceStatusRequest request) {
        updateInvoiceStatus.execute(request.getInvoiceId(), request.getStatus());
    }
}
