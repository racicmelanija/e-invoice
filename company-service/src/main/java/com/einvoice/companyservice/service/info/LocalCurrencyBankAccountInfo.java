package com.einvoice.companyservice.service.info;

import com.einvoice.companyservice.model.Company;
import com.einvoice.companyservice.model.EBankingFormat;
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
public class LocalCurrencyBankAccountInfo {

    private String accountNumber;
    private EBankingFormat eBankingFormat;
}
