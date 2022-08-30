package com.einvoice.companyservice.repository;

import com.einvoice.companyservice.model.LocalCurrencyBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocalCurrencyBankAccountRepository extends JpaRepository<LocalCurrencyBankAccount, UUID> {
}
