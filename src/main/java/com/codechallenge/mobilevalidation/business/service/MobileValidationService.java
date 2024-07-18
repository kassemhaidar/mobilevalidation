package com.codechallenge.mobilevalidation.business.service;

import com.codechallenge.mobilevalidation.business.domain.exception.CustomException;
import com.codechallenge.mobilevalidation.business.domain.exception.InvalidMobileNumberException;
import com.codechallenge.mobilevalidation.business.domain.response.MobileValidationResponse;

/**
 * Interface for validating mobile numbers.
 */
public interface MobileValidationService {

    /**
     * Validates a mobile number using an external API.
     *
     * @param number the mobile number to validate
     * @return the MobileValidationResponse object
     * @throws InvalidMobileNumberException if the mobile number format is invalid
     * @throws CustomException if an unexpected error occurs during validation
     */
    MobileValidationResponse validateMobileNumber(String number) throws InvalidMobileNumberException, CustomException;
}

