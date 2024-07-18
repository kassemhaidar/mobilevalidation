package com.codechallenge.mobilevalidation.business.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import feign.codec.ErrorDecoder;

import com.codechallenge.mobilevalidation.business.domain.dto.MobileValidationDto;
import com.codechallenge.mobilevalidation.business.domain.exception.CustomFeignErrorDecoder;
import com.codechallenge.mobilevalidation.business.service.MobileValidationApi;

/**
 * Feign client interface for mobile number validation.
 */
@FeignClient(value = "mobileValidation", url = "${apilayer.api.url}", configuration = MobileValidationApi.CustomFeignConfiguration.class)
public interface MobileValidationApi {
	
	/**
     * Validates a mobile number.
     *
     * @param number the mobile number to validate
     * @param apiKey the API key for authentication
     * @return the mobile validation result as a {@link MobileValidationDto}
     */
	@GetMapping(value = "/validate")
	MobileValidationDto validateNumber(@RequestParam(name = "number") String number, 
			@RequestHeader("apikey") String apiKey);
	
	// Custom Feign configuration class for error handling
    class CustomFeignConfiguration {
        public ErrorDecoder errorDecoder() {
            return new CustomFeignErrorDecoder();
        }
    }
}