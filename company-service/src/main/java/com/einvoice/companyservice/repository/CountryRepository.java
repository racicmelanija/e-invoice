package com.einvoice.companyservice.repository;

import com.einvoice.companyservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
}
