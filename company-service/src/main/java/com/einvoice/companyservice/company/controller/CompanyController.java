package com.einvoice.companyservice.company.controller;

import com.einvoice.companyservice.company.model.Company;
import com.einvoice.companyservice.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity registerCompany(@Valid @RequestBody Company company){
        companyService.registerCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
