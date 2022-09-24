package com.einvoice.invoiceservice.repository;

import com.einvoice.invoiceservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}
