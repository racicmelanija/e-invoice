package com.einvoice.companyservice.company.service;

import com.einvoice.companyservice.company.model.Company;

public interface CompanyService {
    void registerCompany(Company company);
    boolean isRegistered(String taxIdentificationNumber, String companyRegistrationNumber);
}
