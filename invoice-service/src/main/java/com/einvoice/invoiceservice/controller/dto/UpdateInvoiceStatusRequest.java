package com.einvoice.invoiceservice.controller.dto;

import com.einvoice.invoiceservice.model.InvoiceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class UpdateInvoiceStatusRequest {

    @NotNull
    private UUID invoiceId;
    private InvoiceStatus status;
}
