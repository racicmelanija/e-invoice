package com.einvoice.companyservice.service;

import com.einvoice.companyservice.model.Company;
import com.einvoice.companyservice.repository.CompanyRepository;
import com.einvoice.companyservice.service.info.RegisterCompanyInfo;
import com.einvoice.companyservice.exception.CompanyAlreadyRegisteredException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterCompany {

    private final CompanyRepository companyRepository;
    private final CreateAddress createAddress;

    @Transactional(rollbackFor = Throwable.class)
    public void execute(RegisterCompanyInfo info) {
        if(isRegistered(info.getTaxIdentificationNumber(), info.getCompanyRegistrationNumber())) {
            throw new CompanyAlreadyRegisteredException();
        }

        companyRepository.save(Company.builder()
                .companyName(info.getCompanyName())
                .taxIdentificationNumber(info.getTaxIdentificationNumber())
                .companyRegistrationNumber(info.getCompanyRegistrationNumber())
                .phoneNumber(info.getPhoneNumber())
                .address(createAddress.execute(info.getAddressInfo()))
                .build());
    }

    private boolean isRegistered(String taxIdentificationNumber, String companyRegistrationNumber) {
        return companyRepository.findCompanyByTaxNumber(taxIdentificationNumber).isPresent()
                || companyRepository.findCompanyByRegistrationNumber(companyRegistrationNumber).isPresent();
    }
}
