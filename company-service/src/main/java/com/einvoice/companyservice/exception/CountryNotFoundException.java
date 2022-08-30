package com.einvoice.companyservice.exception;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException() {
        super("Country with the given id doesn't exist");
    }
}
