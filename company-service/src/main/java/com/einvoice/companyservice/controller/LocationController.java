package com.einvoice.companyservice.controller;

import com.einvoice.companyservice.controller.dto.GetCitiesResponse;
import com.einvoice.companyservice.controller.dto.GetCountriesResponse;
import com.einvoice.companyservice.service.GetCities;
import com.einvoice.companyservice.service.GetCountries;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.einvoice.companyservice.converter.LocationConverter.toGetCitiesResponse;
import static com.einvoice.companyservice.converter.LocationConverter.toGetCountriesResponse;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final GetCities getCities;
    private final GetCountries getCountries;

    @GetMapping(value = "/cities")
    public ResponseEntity<GetCitiesResponse> getAllCities(){
        return new ResponseEntity<>(toGetCitiesResponse(getCities.execute()), HttpStatus.OK);
    }

    @GetMapping(value = "/countries")
    public ResponseEntity<GetCountriesResponse> getAllCountries(){
        return new ResponseEntity<>(toGetCountriesResponse(getCountries.execute()), HttpStatus.OK);
    }
}
