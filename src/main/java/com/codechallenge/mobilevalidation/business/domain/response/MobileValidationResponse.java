package com.codechallenge.mobilevalidation.business.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response representing the mobile number validation to be returned by the API.
 */
@Getter
@Setter
@NoArgsConstructor
public class MobileValidationResponse {
	
	/**
     * The country code of the mobile number.
     */
    private String countryCode;
    
    /**
     * The country name of the mobile number.
     */
    private String countryName;
    
    /**
     * The operator name of the mobile number.
     */
	private String operatorName;
	
	/**
     * The mobile number.
     */
    private String mobileNumber;
    
    /**
     * Indicates whether the mobile number is valid.
     */
    private boolean valid;

    public MobileValidationResponse(String countryCode, String countryName, String operatorName, String mobileNumber,
			boolean valid) {
		super();
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.operatorName = operatorName;
		this.mobileNumber = mobileNumber;
		this.valid = valid;
	}    
}
