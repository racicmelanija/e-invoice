package com.einvoice.invoiceservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateInvoiceRequest {

    @NotNull
    private UUID companyId;

    @NotBlank
    private String companyTaxId;

    @NotNull
    private UUID clientId;

    @NotBlank
    private String clientTaxId;

    @NotBlank
    private String bankAccount;

    @NotBlank
    private String referenceNumber;

    @NotEmpty
    @Valid
    private List<InvoiceItemRequest> invoiceItems;
}
