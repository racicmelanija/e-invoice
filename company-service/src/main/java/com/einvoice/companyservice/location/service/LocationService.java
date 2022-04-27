package com.einvoice.companyservice.location.service;


import com.einvoice.companyservice.location.model.City;
import com.einvoice.companyservice.location.model.Country;

import java.util.List;

public interface LocationService {
    List<Country> getAllCountries();
    List<City> getAllCities();
    City findCityByName(String name);
}
