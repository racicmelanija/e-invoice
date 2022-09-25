package com.einvoice.companyservice.controller;

import com.einvoice.companyservice.annotation.Authorize;
import com.einvoice.companyservice.controller.dto.GetCompaniesResponse;
import com.einvoice.companyservice.controller.dto.GetCompanyResponse;
import com.einvoice.companyservice.controller.dto.RegisterCompanyRequest;
import com.einvoice.companyservice.model.Company;
import com.einvoice.companyservice.service.GetCompanies;
import com.einvoice.companyservice.service.GetCompanyById;
import com.einvoice.companyservice.service.RegisterCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.UUID;

import static com.einvoice.companyservice.converter.CompanyConverter.toGetCompaniesInfo;
import static com.einvoice.companyservice.converter.CompanyConverter.toGetCompaniesResponse;
import static com.einvoice.companyservice.converter.CompanyConverter.toGetCompanyResponse;
import static com.einvoice.companyservice.converter.CompanyConverter.toRegisterCompanyInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/companies")
public class CompanyController {

    private final RegisterCompany registerCompany;
    private final GetCompanies getCompanies;
    private final GetCompanyById getCompanyById;

    @PostMapping
    @Authorize(roles = {"COMPANY_OWNER"})
    public ResponseEntity<Void> registerCompany(JwtAuthenticationToken jwt, @Valid @RequestBody RegisterCompanyRequest request){
        registerCompany.execute(toRegisterCompanyInfo(request, UUID.fromString(jwt.getName())));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GetCompaniesResponse> getCompanies(@RequestParam UUID notClientsWith, @RequestParam String search, @RequestParam int page, @RequestParam int size) {
        Page<Company> result = getCompanies.execute(toGetCompaniesInfo(notClientsWith, search, page, size));
        return new ResponseEntity<>(toGetCompaniesResponse(result), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCompanyResponse> getCompany(@PathVariable UUID id) {
        return new ResponseEntity<>(toGetCompanyResponse(getCompanyById.execute(id)), HttpStatus.OK);
    }
}
