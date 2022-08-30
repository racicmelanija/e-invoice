package com.einvoice.companyservice.service;

import com.einvoice.companyservice.model.City;
import com.einvoice.companyservice.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCities {

    private final CityRepository cityRepository;

    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<City> execute() {
        return cityRepository.findAll();
    }
}
