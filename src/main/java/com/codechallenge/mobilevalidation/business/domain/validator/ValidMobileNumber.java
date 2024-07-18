package com.codechallenge.mobilevalidation.business.domain.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.codechallenge.mobilevalidation.constant.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to validate the format of a mobile number using {@link MobileNumberValidator}.
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileNumberValidator.class)
public @interface ValidMobileNumber {

    /**
     * Default error message when the mobile number format is invalid.
     *
     * @return the error message
     */
    String message() default Constants.INVALID_MOBILE_FORMAT;

    /**
     * Groups of constraints this constraint belongs to.
     *
     * @return the groups
     */
    Class<?>[] groups() default {};

    /**
     * Additional data to be carried with the annotation.
     *
     * @return the payload
     */
    Class<? extends Payload>[] payload() default {};
}