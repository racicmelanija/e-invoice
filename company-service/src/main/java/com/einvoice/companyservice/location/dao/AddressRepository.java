package com.einvoice.companyservice.location.dao;

import com.einvoice.companyservice.location.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
