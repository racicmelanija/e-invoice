package com.einvoice.companyservice.controller;

import com.einvoice.companyservice.annotation.Authorize;
import com.einvoice.companyservice.controller.dto.RegisterCompanyRequest;
import com.einvoice.companyservice.service.RegisterCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.UUID;

import static com.einvoice.companyservice.converter.CompanyConverter.toRegisterCompanyInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/companies")
public class CompanyController {

    private final RegisterCompany registerCompany;

    @PostMapping
    @Authorize(roles = {"COMPANY_OWNER"})
    public ResponseEntity registerCompany(JwtAuthenticationToken jwt, @Valid @RequestBody RegisterCompanyRequest request){
        registerCompany.execute(toRegisterCompanyInfo(request, UUID.fromString(jwt.getName())));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
