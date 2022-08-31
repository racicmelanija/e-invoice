package com.einvoice.companyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            CityNotFoundException.class,
            CountryNotFoundException.class,
            CompanyOwnerNotFoundException.class
    })
    public ResponseEntity<ExceptionResponse> handleNotFound(Exception ex) {
        return new ResponseEntity<>(getResponse(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            CompanyAlreadyRegisteredException.class,
            UserAlreadyRegisteredException.class
    })
    public ResponseEntity<ExceptionResponse> handleConflict(Exception ex) {
        return new ResponseEntity<>(getResponse(ex), HttpStatus.CONFLICT);
    }

    private ExceptionResponse getResponse(Exception ex) {
        return ExceptionResponse.builder()
                .errorMessage(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
