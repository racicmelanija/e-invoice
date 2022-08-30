package com.einvoice.companyservice.service;

import com.einvoice.companyservice.exception.CompanyOwnerNotFoundException;
import com.einvoice.companyservice.model.Company;
import com.einvoice.companyservice.model.Employment;
import com.einvoice.companyservice.repository.CompanyRepository;
import com.einvoice.companyservice.repository.EmploymentRepository;
import com.einvoice.companyservice.repository.UserRepository;
import com.einvoice.companyservice.service.info.RegisterCompanyInfo;
import com.einvoice.companyservice.exception.CompanyAlreadyRegisteredException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.einvoice.companyservice.model.Role.COMPANY_OWNER;

@Service
@RequiredArgsConstructor
public class RegisterCompany {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final EmploymentRepository employmentRepository;
    private final CreateAddress createAddress;
    private final CreateLocalCurrencyBankAccount createLocalCurrencyBankAccount;
    private final CreateForeignCurrencyBankAccount createForeignCurrencyBankAccount;

    @Transactional(rollbackFor = Throwable.class)
    public void execute(RegisterCompanyInfo info) {
        if(isRegistered(info.getTaxIdentificationNumber(), info.getCompanyRegistrationNumber())) {
            throw new CompanyAlreadyRegisteredException();
        }

        Company company = companyRepository.save(Company.builder()
                .companyName(info.getCompanyName())
                .taxIdentificationNumber(info.getTaxIdentificationNumber())
                .companyRegistrationNumber(info.getCompanyRegistrationNumber())
                .phoneNumber(info.getPhoneNumber())
                .address(createAddress.execute(info.getAddressInfo()))
                .build());

        registerBankAccounts(info, company);
        registerCompanyOwnersEmployment(info, company);
    }

    private void registerCompanyOwnersEmployment(RegisterCompanyInfo info, Company company) {
        userRepository.findById(info.getCompanyOwnerId()).orElseThrow(CompanyOwnerNotFoundException::new);
        employmentRepository.save(Employment.builder()
                .companyId(company.getId())
                .employeeId(info.getCompanyOwnerId())
                .role(COMPANY_OWNER)
                .build());
    }

    private void registerBankAccounts(RegisterCompanyInfo info, Company company) {
        if(info.getLocalCurrencyBankAccountInfo() != null) {
            createLocalCurrencyBankAccount.execute(info.getLocalCurrencyBankAccountInfo(), company);
        }
        if(info.getForeignCurrencyBankAccountInfo() != null) {
            createForeignCurrencyBankAccount.execute(info.getForeignCurrencyBankAccountInfo(), company);
        }
    }

    private boolean isRegistered(String taxIdentificationNumber, String companyRegistrationNumber) {
        return companyRepository.findCompanyByTaxNumber(taxIdentificationNumber).isPresent()
                || companyRepository.findCompanyByRegistrationNumber(companyRegistrationNumber).isPresent();
    }
}
