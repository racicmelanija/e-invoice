package com.einvoice.companyservice.service;

import com.einvoice.companyservice.exception.CompanyNotFoundException;
import com.einvoice.companyservice.model.Company;
import com.einvoice.companyservice.repository.CompanyRepository;
import com.einvoice.companyservice.service.result.GetCompanyByIdResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCompanyById {

    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public GetCompanyByIdResult execute(UUID id) {
        return companyRepository.getCompany(id).orElseThrow(CompanyNotFoundException::new);
    }
}
