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
public class ForeignCurrencyBankAccount extends BankAccount {

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne
    private Company company;

    public ForeignCurrencyBankAccount(String accountNumber, Currency currency, Company company) {
        super(accountNumber);
        this.currency = currency;
        this.company = company;
    }
}
