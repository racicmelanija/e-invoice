package com.einvoice.companyservice.repository;

import com.einvoice.companyservice.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
}
