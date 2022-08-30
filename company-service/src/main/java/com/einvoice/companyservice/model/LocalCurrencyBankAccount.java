package com.einvoice.companyservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LocalCurrencyBankAccount extends BankAccount {

    @Enumerated(EnumType.STRING)
    private EBankingFormat eBankingFormat;

    @ManyToOne
    private Company company;

    public LocalCurrencyBankAccount(String accountNumber, EBankingFormat eBankingFormat, Company company) {
        super(accountNumber);
        this.eBankingFormat = eBankingFormat;
        this.company = company;
    }
}
