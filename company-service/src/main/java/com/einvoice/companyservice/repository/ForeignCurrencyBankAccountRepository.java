package com.einvoice.companyservice.repository;

import com.einvoice.companyservice.model.ForeignCurrencyBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ForeignCurrencyBankAccountRepository extends JpaRepository<ForeignCurrencyBankAccount, UUID> {
}