package com.einvoice.companyservice.service;

import com.einvoice.companyservice.model.Company;
import com.einvoice.companyservice.model.LocalCurrencyBankAccount;
import com.einvoice.companyservice.repository.LocalCurrencyBankAccountRepository;
import com.einvoice.companyservice.service.info.LocalCurrencyBankAccountInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateLocalCurrencyBankAccount {

    private final LocalCurrencyBankAccountRepository localCurrencyBankAccountRepository;

    @Transactional(rollbackFor = Throwable.class)
    public void execute(LocalCurrencyBankAccountInfo info, Company company) {
        localCurrencyBankAccountRepository.save(
                new LocalCurrencyBankAccount(info.getAccountNumber(), info.getEBankingFormat(), company)
        );
    }
}
