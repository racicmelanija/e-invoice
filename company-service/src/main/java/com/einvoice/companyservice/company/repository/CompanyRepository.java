package com.einvoice.companyservice.company.repository;

import com.einvoice.companyservice.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c from Company c where c.taxIdentificationNumber = :taxIdentificationNumber")
    Optional<Company> findCompanyByTaxNumber(String taxIdentificationNumber);

    @Query("select c from Company c where c.companyRegistrationNumber = :companyRegistrationNumber")
    Optional<Company> findCompanyByRegistrationNumber(String companyRegistrationNumber);
}
