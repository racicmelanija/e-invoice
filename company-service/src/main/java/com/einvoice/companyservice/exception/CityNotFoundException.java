package com.einvoice.companyservice.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException() {
        super("City with the given id doesn't exist.");
    }
}
