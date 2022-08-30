package com.einvoice.companyservice.service;

import com.einvoice.companyservice.model.Company;
import com.einvoice.companyservice.model.ForeignCurrencyBankAccount;
import com.einvoice.companyservice.repository.ForeignCurrencyBankAccountRepository;
import com.einvoice.companyservice.service.info.ForeignCurrencyBankAccountInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateForeignCurrencyBankAccount {

    private final ForeignCurrencyBankAccountRepository foreignCurrencyBankAccountRepository;

    @Transactional(rollbackFor = Throwable.class)
    public void execute(ForeignCurrencyBankAccountInfo info, Company company) {
        foreignCurrencyBankAccountRepository.save(
                new ForeignCurrencyBankAccount(info.getAccountNumber(), info.getCurrency(), company)
        );
    }
}
