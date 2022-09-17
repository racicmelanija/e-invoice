package com.einvoice.companyservice.service;

import com.einvoice.companyservice.repository.EmploymentRepository;
import com.einvoice.companyservice.service.result.GetEmploymentsResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetEmployments {

    private final EmploymentRepository employmentRepository;

    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<GetEmploymentsResult> execute(UUID employeeId) {
        return employmentRepository.getEmployments(employeeId.toString());
    }
}
