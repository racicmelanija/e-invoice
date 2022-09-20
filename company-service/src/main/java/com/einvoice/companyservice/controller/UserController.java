package com.einvoice.companyservice.controller;

import com.einvoice.companyservice.controller.dto.GetRoleInCompanyResponse;
import com.einvoice.companyservice.service.GetRoleInCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final GetRoleInCompany getRoleInCompany;

    @GetMapping("/users/{id}/roles")
    public ResponseEntity<GetRoleInCompanyResponse> getRoleInCompany(@PathVariable UUID id, @RequestParam UUID companyId) {
        return new ResponseEntity<>(new GetRoleInCompanyResponse(getRoleInCompany.execute(companyId, id)), HttpStatus.OK);
    }
}
