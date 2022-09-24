package com.einvoice.invoiceservice.repository;

import com.einvoice.invoiceservice.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, UUID> {
}
