package com.einvoice.companyservice.repository;

import com.einvoice.companyservice.model.CompanyEmployeeId;
import com.einvoice.companyservice.model.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentRepository extends JpaRepository<Employment, CompanyEmployeeId> {
}
