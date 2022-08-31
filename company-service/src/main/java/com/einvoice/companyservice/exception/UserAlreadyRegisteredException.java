package com.einvoice.companyservice.exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException() {
        super("User with the given email address is already registered.");
    }
}
