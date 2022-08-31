package com.einvoice.companyservice.controller;

import com.einvoice.companyservice.annotation.Authorize;
import com.einvoice.companyservice.controller.dto.RegisterEmployeeRequest;
import com.einvoice.companyservice.service.RegisterEmployee;
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

import static com.einvoice.companyservice.converter.EmployeeConverter.toRegisterEmployeeInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final RegisterEmployee registerEmployee;

    @PostMapping
    @Authorize(roles = {"COMPANY_OWNER", "ADMIN"})
    public ResponseEntity<Void> registerEmployee(JwtAuthenticationToken jwt, @Valid @RequestBody RegisterEmployeeRequest request) {
        registerEmployee.execute(toRegisterEmployeeInfo(request, UUID.fromString(jwt.getName())));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
