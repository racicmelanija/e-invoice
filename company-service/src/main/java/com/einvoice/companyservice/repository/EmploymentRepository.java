package com.einvoice.companyservice.repository;

import com.einvoice.companyservice.model.CompanyEmployeeId;
import com.einvoice.companyservice.model.Employment;
import com.einvoice.companyservice.model.Role;
import com.einvoice.companyservice.service.result.GetEmploymentsResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmploymentRepository extends JpaRepository<Employment, CompanyEmployeeId> {

    @Query("select case when count(e) > 0 then true else false end from Employment e " +
            "where e.companyId = :companyId and e.employeeId = :employeeId and (e.role = 'ADMIN' or e.role = 'COMPANY_OWNER')")
    boolean isAdminOrOwnerOfCompany(UUID companyId, UUID employeeId);

    @Query(nativeQuery = true,
            value = "SELECT CAST(e.company_id AS VARCHAR) as companyId, c.company_name as companyName, e.role as role " +
                    "FROM employment e LEFT OUTER JOIN company c ON e.company_id = c.id " +
                    "WHERE CAST(e.employee_id AS VARCHAR) = ?1 " +
                    "LIMIT 1000;")
    List<GetEmploymentsResult> getEmployments(String employeeId);

    @Query("select e.role from Employment e where e.companyId = :companyId and e.employeeId = :employeeId")
    Optional<Role> getRole(UUID companyId, UUID employeeId);
}
