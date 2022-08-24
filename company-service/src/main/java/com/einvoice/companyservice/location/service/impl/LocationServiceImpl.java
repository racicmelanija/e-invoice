package com.einvoice.companyservice.location.service.impl;


import com.einvoice.companyservice.location.dao.CityRepository;
import com.einvoice.companyservice.location.dao.CountryRepository;
import com.einvoice.companyservice.location.model.City;
import com.einvoice.companyservice.location.model.Country;
import com.einvoice.companyservice.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationServiceImpl implements LocationService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    @Autowired
    public LocationServiceImpl(CountryRepository countryRepository, CityRepository cityRepository){
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    @Override
    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    @Override
    public City findCityByName(String name) {
        return cityRepository.findByName(name);
    }
}
