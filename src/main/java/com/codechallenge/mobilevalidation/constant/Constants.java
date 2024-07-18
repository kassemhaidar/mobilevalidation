package com.codechallenge.mobilevalidation.constant;

public class Constants {

	// TODO should be moved to file Constants in a common library and not implemented in every project.
	/* Authentication */
	public static final String UNAUTHORIZED = "Unauthorized";
	public static final String USER = "admin";
	public static final String PASSWORD = "password";
	public static final String ROLE = "ADMIN";
	public static final String RESOURCE_NOT_FOUND = "Resource not found error occurred";
	public static final String GENERIC_ERROR_OCCURED = "Generic error occurred";
	public static final String API_NOT_FOUND = "API Not FOund";
	
	/* Header Attributes */
	public static final String MISSING_REQUIRED_HEADER_ERROR = "MISSING_REQUIRED_HEADER_ERROR";
	public static final String ACCEPT_LANGUAGE_HEADER_KEY = "Accept-Language";
	public static final String CONTENT_LANGUAGE_HEADER_KEY = "Content-Language";
	public static final String DEFAULT_LANGUAGE = "EN";
	
	/* Validation */
	public static final String INVALID_MOBILE_FORMAT = "Invalid mobile format : Should start with '+' optional and contains only numbers";
	public static final String INVALID_MOBILE_NUMBER = "Invalid mobile number";
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	public static final String EXPECTED_ERROR_OCCURED = "An unexpected error occurred";
	
	/* Swagger */
	public static final String MOBILE_VALIDATION = "Mobile Validation";
	public static final String API_MANAGING_MOBILE_VALIDATION = "APIs for managing mobile validaiton";
	public static final String SUCCESSFULLY_VALIDATED_MOBILE_NUMBER = "Successfully validated mobile number";
	public static final String VALIDATE_A_MOBILE_NUMBER_SUMMARY = "Validate a mobile number";
	public static final String VALIDATE_A_MOBILE_NUMBER_DESCRIPTION = "Validate a mobile number where it may start with '+' and contains only numbers.";
}
