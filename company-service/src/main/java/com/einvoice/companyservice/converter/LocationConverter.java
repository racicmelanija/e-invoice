package com.einvoice.companyservice.converter;

import com.einvoice.companyservice.controller.dto.GetCitiesResponse;
import com.einvoice.companyservice.controller.dto.GetCityResponse;
import com.einvoice.companyservice.controller.dto.GetCountriesResponse;
import com.einvoice.companyservice.controller.dto.GetCountryResponse;
import com.einvoice.companyservice.model.City;
import com.einvoice.companyservice.model.Country;

import java.util.List;
import java.util.stream.Collectors;

public class LocationConverter {

    public static GetCitiesResponse toGetCitiesResponse(List<City> cities) {
        return GetCitiesResponse.builder()
                .cities(cities.stream()
                        .map(city -> GetCityResponse.builder()
                                .id(city.getId())
                                .name(city.getName())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public static GetCountriesResponse toGetCountriesResponse(List<Country> countries) {
        return GetCountriesResponse.builder()
                .countries(countries.stream()
                        .map(country -> GetCountryResponse.builder()
                                .id(country.getId())
                                .name(country.getName())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
