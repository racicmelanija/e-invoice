package com.einvoice.companyservice.company.service.impl;

import com.einvoice.companyservice.company.model.Company;
import com.einvoice.companyservice.company.repository.CompanyRepository;
import com.einvoice.companyservice.company.service.CompanyService;
import com.einvoice.companyservice.exception.CompanyAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void registerCompany(Company company) {
        if(isRegistered(company.getTaxIdentificationNumber(), company.getCompanyRegistrationNumber())){
            throw new CompanyAlreadyRegisteredException();
        }
        companyRepository.save(company);
    }

    @Override
    public boolean isRegistered(String taxIdentificationNumber, String companyRegistrationNumber) {
        return companyRepository.findCompanyByTaxNumber(taxIdentificationNumber).isPresent() || companyRepository.findCompanyByRegistrationNumber(companyRegistrationNumber).isPresent();
    }
}