package com.einvoice.companyservice.repository;

import com.einvoice.companyservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
