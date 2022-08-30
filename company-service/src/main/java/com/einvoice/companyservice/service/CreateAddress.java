package com.einvoice.companyservice.service;

import com.einvoice.companyservice.exception.CityNotFoundException;
import com.einvoice.companyservice.repository.AddressRepository;
import com.einvoice.companyservice.repository.CityRepository;
import com.einvoice.companyservice.model.Address;
import com.einvoice.companyservice.service.info.AddressInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateAddress {

    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;

    @Transactional(rollbackFor = Throwable.class)
    public Address execute(AddressInfo info) {
        return addressRepository.save(Address.builder()
                .address(info.getAddress())
                .city(cityRepository.findById(info.getCityId()).orElseThrow(CityNotFoundException::new))
                .build());
    }
}
