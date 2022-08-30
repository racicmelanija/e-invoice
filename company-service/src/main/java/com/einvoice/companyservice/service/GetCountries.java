package com.einvoice.companyservice.service;

import com.einvoice.companyservice.model.Country;
import com.einvoice.companyservice.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCountries {

    private final CountryRepository countryRepository;

    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<Country> execute() {
        return countryRepository.findAll();
    }
}
