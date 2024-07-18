package com.codechallenge.mobilevalidation.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codechallenge.mobilevalidation.business.domain.exception.CustomException;
import com.codechallenge.mobilevalidation.business.domain.dto.MobileValidationDto;
import com.codechallenge.mobilevalidation.business.domain.exception.InvalidMobileNumberException;
import com.codechallenge.mobilevalidation.business.domain.response.MobileValidationResponse;
import com.codechallenge.mobilevalidation.business.service.MessageService;
import com.codechallenge.mobilevalidation.business.service.MobileValidationApi;
import com.codechallenge.mobilevalidation.business.service.MobileValidationService;
import com.codechallenge.mobilevalidation.component.RequestReaderComponent;
import com.codechallenge.mobilevalidation.constant.Constants;

import feign.FeignException;

/**
 * Service class for validating mobile number
 */
@Service
public class MobileValidationServiceImpl implements MobileValidationService {

	@Autowired
	private MobileValidationApi mobileValidationInterface;

	@Value("${apilayer.api.key}")
	private String apiKey;
	
	@Autowired
	MessageService messageService;
	@Autowired
	RequestReaderComponent requestReaderComponent;

	private static final Logger log = LoggerFactory.getLogger(MobileValidationServiceImpl.class);

	/**
     * {@inheritDoc}
     */
	public MobileValidationResponse validateMobileNumber(String number) throws CustomException {
		String invalidMobileNumber = messageService.getMessage("invalid.mobile.number",null,requestReaderComponent.getLanguage());
		try {
			log.info("in validateMobileNumber() >>> number sent is : " + number);
			MobileValidationDto dto = mobileValidationInterface.validateNumber(number, apiKey);
			MobileValidationResponse response = convertDtoToResponse(dto);
			/*
			 * TODO handle different types of exception (Unauthorized, API not found,
			 * Internal Server Error) and throw these exceptions in custom exceptions.
			 */
			if (response != null && !response.isValid()) {
				log.error("in validateMobileNumber() >>> " + 
						invalidMobileNumber + " : " + number);
				throw new InvalidMobileNumberException(invalidMobileNumber + " : " + number);
			}
			// TODO MobileValidationResponse.toString() to be readable response
			log.info("in validateMobileNumber() >>> response returned is : " + response.toString());
			return response;
		} catch (FeignException fe) {
			fe.printStackTrace();
			log.error("in validateMobileNumber() >>> status: " + fe.status() + ", message: " + fe.getMessage());
			if (fe.status() == 400 && fe.getMessage().contains(Constants.INVALID_MOBILE_NUMBER)) {
				throw new CustomException(
						invalidMobileNumber);
			}
			if (fe.status() == 404) {
				throw new CustomException(
						messageService.getMessage("api.not.found",null,requestReaderComponent.getLanguage()));
			}
			if (fe.status() == 401) {
				throw new CustomException(
						messageService.getMessage("unauthorized",null,requestReaderComponent.getLanguage()));
			}
			throw new CustomException(
					messageService.getMessage("internal.server.error",null,requestReaderComponent.getLanguage()));
		}
	}

	/**
	 * Converts a MobileValidationDto entity to a MobileValidationResponse.
	 *
	 * @param dto the MobileValidationDto to convert
	 * @return the converted MobileValidation response
	 *         {@link MobileValidationResponse}
	 */
	private MobileValidationResponse convertDtoToResponse(MobileValidationDto dto) {
		return new MobileValidationResponse(dto.getCountry_code(), dto.getCountry_name(), dto.getCarrier(),
				dto.getNumber(), dto.isValid());
	}
}
