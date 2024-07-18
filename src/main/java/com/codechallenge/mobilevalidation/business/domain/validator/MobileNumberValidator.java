package com.codechallenge.mobilevalidation.business.domain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for validating mobile numbers based on custom constraints.
 */
public class MobileNumberValidator implements ConstraintValidator<ValidMobileNumber, String> {

    /**
     * Initialize the validator.
     *
     * @param constraintAnnotation the annotation instance for this validator
     */
    @Override
    public void initialize(ValidMobileNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Validates if the given mobile number string is in a valid format.
     * Mobile number may start with + (optional) and contains one or more digits
     *
     * @param value   the mobile number string to validate
     * @param context validation context
     * @return true if the mobile number is valid, false otherwise
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        // regular expression to start with '+' and continue with digits only
        return value.matches("^\\+?\\d+$");
    }
}
