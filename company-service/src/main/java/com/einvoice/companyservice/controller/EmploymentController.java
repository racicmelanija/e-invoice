package com.einvoice.companyservice.controller;

import com.einvoice.companyservice.controller.dto.CreateEmploymentRequest;
import com.einvoice.companyservice.controller.dto.GetEmploymentsResponse;
import com.einvoice.companyservice.service.CreateEmployment;
import com.einvoice.companyservice.service.GetEmployments;
import com.einvoice.companyservice.service.result.GetEmploymentsResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.einvoice.companyservice.converter.EmploymentConverter.toCreateEmploymentInfo;
import static com.einvoice.companyservice.converter.EmploymentConverter.toGetEmploymentsResponse;

@RestController
@RequiredArgsConstructor
public class EmploymentController {

    private final GetEmployments getEmployments;
    private final CreateEmployment createEmployment;

    @GetMapping("/employments")
    public ResponseEntity<GetEmploymentsResponse> getEmployments(JwtAuthenticationToken jwt) {
        List<GetEmploymentsResult> result = getEmployments.execute(UUID.fromString(jwt.getName()));
        return new ResponseEntity<>(toGetEmploymentsResponse(result), HttpStatus.OK);
    }

    @PostMapping("/employments")
    public void createEmployment(@Valid @RequestBody CreateEmploymentRequest request) {
        createEmployment.execute(toCreateEmploymentInfo(request));
    }
}
