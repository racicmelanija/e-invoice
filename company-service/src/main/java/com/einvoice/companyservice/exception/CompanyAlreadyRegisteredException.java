package com.einvoice.companyservice.exception;

public class CompanyAlreadyRegisteredException extends RuntimeException{

    public CompanyAlreadyRegisteredException(){
        super("Company with the given tax identification number or registration number is already registered.");
    }
}
