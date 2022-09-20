package com.einvoice.companyservice.service;

import com.einvoice.companyservice.exception.UnauthorizedException;
import com.einvoice.companyservice.model.CompanyEmployeeId;
import com.einvoice.companyservice.model.Employment;
import com.einvoice.companyservice.repository.EmploymentRepository;
import com.einvoice.companyservice.service.info.CreateEmploymentInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.einvoice.companyservice.model.Role.COMPANY_OWNER;

@Service
@RequiredArgsConstructor
public class CreateEmployment {

    private final EmploymentRepository employmentRepository;

    @Transactional(rollbackFor = Throwable.class)
    public void execute(CreateEmploymentInfo info) {
        employmentRepository.findById(new CompanyEmployeeId(info.getCompanyId(), info.getUserId()))
                .ifPresentOrElse(
                        employment -> {
                            if (employment.getRole() == COMPANY_OWNER) {
                                throw new UnauthorizedException();
                            }
                            employment.setRole(info.getRole());
                        },
                        () -> employmentRepository.save(Employment.builder()
                                .companyId(info.getCompanyId())
                                .employeeId(info.getUserId())
                                .role(info.getRole())
                                .build())
                );
    }
}
