package com.einvoice.companyservice.exception;

public class CompanyOwnerNotFoundException extends RuntimeException {

    public CompanyOwnerNotFoundException() {
        super("Company owner not found in our database.");
    }
}
