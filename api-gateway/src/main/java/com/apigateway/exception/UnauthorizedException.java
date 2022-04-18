package com.apigateway.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(){
        super("User is not authorized to access the requested resource.");
    }
}
