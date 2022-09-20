package com.einvoice.companyservice.service;

import com.einvoice.companyservice.model.CompanyEmployeeId;
import com.einvoice.companyservice.model.Employment;
import com.einvoice.companyservice.repository.EmploymentRepository;
import com.einvoice.companyservice.service.info.CreateEmploymentInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateEmployment {

    private final EmploymentRepository employmentRepository;

    @Transactional(rollbackFor = Throwable.class)
    public void execute(CreateEmploymentInfo info) {
        employmentRepository.findById(new CompanyEmployeeId(info.getCompanyId(), info.getUserId()))
                .ifPresentOrElse(
                        employment -> employment.setRole(info.getRole()),
                        () -> employmentRepository.save(Employment.builder()
                                .companyId(info.getCompanyId())
                                .employeeId(info.getUserId())
                                .role(info.getRole())
                                .build())
                );
    }
}
