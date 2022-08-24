package com.einvoice.companyservice.location.dao;

import com.einvoice.companyservice.location.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String name);
}
