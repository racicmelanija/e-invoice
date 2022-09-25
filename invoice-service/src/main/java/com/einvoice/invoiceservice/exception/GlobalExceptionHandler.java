package com.einvoice.invoiceservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvoiceNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleNotFound(Exception ex) {
        return new ResponseEntity<>(getResponse(ex), HttpStatus.NOT_FOUND);
    }

    private ExceptionResponse getResponse(Exception ex) {
        return ExceptionResponse.builder()
                .errorMessage(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
