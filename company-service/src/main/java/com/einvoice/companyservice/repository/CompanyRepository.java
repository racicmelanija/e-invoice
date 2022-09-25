package com.einvoice.companyservice.repository;

import com.einvoice.companyservice.model.Company;
import com.einvoice.companyservice.service.result.GetCompanyByIdResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    @Query("select c from Company c where c.taxIdentificationNumber = :taxIdentificationNumber")
    Optional<Company> findCompanyByTaxNumber(String taxIdentificationNumber);

    @Query("select c from Company c where c.companyRegistrationNumber = :companyRegistrationNumber")
    Optional<Company> findCompanyByRegistrationNumber(String companyRegistrationNumber);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM company " +
                    "WHERE id NOT IN (SELECT client_id " +
                    "FROM collaboration " +
                    "WHERE company_id = ?1) " +
                    "AND id != ?1 " +
                    "AND LOWER(CONCAT(company_name, tax_identification_number, company_registration_number)) LIKE LOWER(CONCAT('%', ?2, '%'))")
    Page<Company> getCompanies(UUID notClientsWith, String search, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT c.tax_identification_number as taxId, b.account_number as bankAccount " +
                    "FROM company c LEFT OUTER JOIN local_currency_bank_account b ON c.id = b.company_id " +
                    "WHERE c.id = ?1 " +
                    "LIMIT 1")
    Optional<GetCompanyByIdResult> getCompany(UUID id);
}
