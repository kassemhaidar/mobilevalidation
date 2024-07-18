package com.codechallenge.mobilevalidation.business.domain.exception;

import com.codechallenge.mobilevalidation.business.domain.exception.InvalidMobileNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for mobile validation related exceptions.
 */
@RestControllerAdvice
public class MobileValidationExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(MobileValidationExceptionHandler.class);

    /**
     * Handles InvalidMobileNumberException.
     *
     * @param ex the InvalidMobileNumberException instance
     * @return ResponseEntity with status 400 and exception message
     */
    @ExceptionHandler(InvalidMobileNumberException.class)
    public ResponseEntity<String> handleInvalidMobileNumberException(InvalidMobileNumberException ex) {
    	log.error("in handleInvalidMobileNumberException() >>> " + ex.getMessage());
    	// TODO handle different types of exception (Unauthorized, API not found, Internal Server Error)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}