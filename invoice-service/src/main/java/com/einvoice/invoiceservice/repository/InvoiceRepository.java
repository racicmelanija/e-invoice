package com.einvoice.invoiceservice.repository;

import com.einvoice.invoiceservice.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    Page<Invoice> findAllByCompanyId(UUID companyId, Pageable pageable);

    Page<Invoice> findAllByClientId(UUID clientId, Pageable pageable);
}
