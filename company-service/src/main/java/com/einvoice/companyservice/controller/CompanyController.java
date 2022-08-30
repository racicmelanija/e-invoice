package com.einvoice.companyservice.controller;

import com.einvoice.companyservice.controller.dto.RegisterCompanyRequest;
import com.einvoice.companyservice.service.RegisterCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.einvoice.companyservice.converter.CompanyConverter.toRegisterCompanyInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/companies")
public class CompanyController {

    private final RegisterCompany registerCompany;

    @PostMapping
    public ResponseEntity registerCompany(@Valid @RequestBody RegisterCompanyRequest request){
        registerCompany.execute(toRegisterCompanyInfo(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
