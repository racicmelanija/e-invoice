package com.einvoice.companyservice.repository;

import com.einvoice.companyservice.model.CompanyEmployeeId;
import com.einvoice.companyservice.model.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EmploymentRepository extends JpaRepository<Employment, CompanyEmployeeId> {

    @Query("select case when count(e) > 0 then true else false end from Employment e " +
            "where e.companyId = :companyId and e.employeeId = :employeeId and (e.role = 'ADMIN' or e.role = 'COMPANY_OWNER')")
    boolean isAdminOrOwnerOfCompany(UUID companyId, UUID employeeId);
}
