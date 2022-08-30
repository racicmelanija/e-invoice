package com.einvoice.companyservice.repository;

import com.einvoice.companyservice.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    @Query("select c from Company c where c.taxIdentificationNumber = :taxIdentificationNumber")
    Optional<Company> findCompanyByTaxNumber(String taxIdentificationNumber);

    @Query("select c from Company c where c.companyRegistrationNumber = :companyRegistrationNumber")
    Optional<Company> findCompanyByRegistrationNumber(String companyRegistrationNumber);
}
