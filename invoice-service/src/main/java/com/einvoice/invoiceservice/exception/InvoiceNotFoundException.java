package com.einvoice.invoiceservice.exception;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException() {
        super("Invoice not found");
    }
}
