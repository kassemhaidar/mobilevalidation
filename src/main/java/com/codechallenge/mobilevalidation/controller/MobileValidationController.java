package com.codechallenge.mobilevalidation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codechallenge.mobilevalidation.business.domain.exception.CustomException;
import com.codechallenge.mobilevalidation.business.domain.exception.InvalidMobileNumberException;
import com.codechallenge.mobilevalidation.business.domain.response.MobileValidationResponse;
import com.codechallenge.mobilevalidation.business.service.MobileValidationService;
import com.codechallenge.mobilevalidation.constant.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * REST controller for mobile validation.
 */
@RestController
@RequestMapping(path = "/api/mobile")
@Tag(name = Constants.MOBILE_VALIDATION, description = Constants.API_MANAGING_MOBILE_VALIDATION)
@Validated
public class MobileValidationController {
	@Autowired
    private MobileValidationService mobileValidationService;

	/**
     * Validate a mobile number. (GET /api/mobile/validate)
     *
     * @param number the mobile number to validate
     * @return the found mobile number (ResponseEntity<?>)
	 * @throws CustomException 
     * @throws InvalidMobileNumberException if mobile number is invalid
     */
    @Operation(summary = Constants.VALIDATE_A_MOBILE_NUMBER_SUMMARY, 
    			description = Constants.VALIDATE_A_MOBILE_NUMBER_DESCRIPTION)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = Constants.SUCCESSFULLY_VALIDATED_MOBILE_NUMBER),
        @ApiResponse(responseCode = "400", description = Constants.INVALID_MOBILE_NUMBER, content = @Content),
        @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED, content = @Content)
    })
    @GetMapping("/validate")
    public ResponseEntity<?> validateMobileNumber(@RequestParam(name = "number") String number) throws InvalidMobileNumberException, CustomException {
    	MobileValidationResponse response = mobileValidationService.validateMobileNumber(number);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
