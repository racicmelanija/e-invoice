package com.einvoice.companyservice.service.info;

import com.einvoice.companyservice.model.Company;
import com.einvoice.companyservice.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ForeignCurrencyBankAccountInfo {

    private String accountNumber;
    private Currency currency;
}
