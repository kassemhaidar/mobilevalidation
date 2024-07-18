package com.codechallenge.mobilevalidation.business.domain.exception;

/**
 * Exception thrown when a mobile number is invalid.
 */
public class InvalidMobileNumberException extends RuntimeException {

    private static final long serialVersionUID = 8449068229036384088L;

    /**
     * Constructs a new InvalidMobileNumberException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidMobileNumberException(String message) {
        super(message);
    }
}