package com.einvoice.companyservice.service;

import com.einvoice.companyservice.model.Company;
import com.einvoice.companyservice.repository.CompanyRepository;
import com.einvoice.companyservice.service.info.GetCompaniesInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetCompanies {

    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public Page<Company> execute(GetCompaniesInfo info) {
        return companyRepository.getCompanies(info.getNotClientsWith(), info.getSearch(),
                PageRequest.of(info.getPage(), info.getSize()));
    }
}
