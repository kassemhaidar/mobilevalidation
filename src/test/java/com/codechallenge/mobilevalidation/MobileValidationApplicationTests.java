package com.codechallenge.mobilevalidation;

import com.codechallenge.mobilevalidation.business.domain.dto.MobileValidationDto;
import com.codechallenge.mobilevalidation.business.domain.exception.CustomException;
import com.codechallenge.mobilevalidation.business.domain.exception.InvalidMobileNumberException;
import com.codechallenge.mobilevalidation.business.domain.response.MobileValidationResponse;
import com.codechallenge.mobilevalidation.business.service.MessageService;
import com.codechallenge.mobilevalidation.business.service.MobileValidationApi;
import com.codechallenge.mobilevalidation.business.service.impl.MobileValidationServiceImpl;
import com.codechallenge.mobilevalidation.component.RequestReaderComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MobileValidationApplicationTests {

    @InjectMocks
    private MobileValidationServiceImpl mobileValidationServiceImpl;

    @Mock
    private MobileValidationApi mobileValidationInterface;
	
    @Mock
	MessageService messageService;
	@Mock
	RequestReaderComponent requestReaderComponent;

    @BeforeEach
    public void setUp() {
        // Set the apiKey field in the service class
        ReflectionTestUtils.setField(mobileValidationServiceImpl, "apiKey", "dummyApiKey");
    }

    @Test
    public void testValidateMobileNumber_ValidNumber() throws InvalidMobileNumberException, CustomException {
        // Mock the MobileValidationDto response
        MobileValidationDto mockDto = new MobileValidationDto();
        mockDto.setCountry_code("US");
        mockDto.setCountry_name("United States");
        mockDto.setCarrier("AT&T");
        mockDto.setNumber("+1234567890");
        mockDto.setValid(true);

        when(mobileValidationInterface.validateNumber(anyString(), anyString())).thenReturn(mockDto);

        // Call the service method
        MobileValidationResponse response = mobileValidationServiceImpl.validateMobileNumber("+1234567890");

        // Verify the response
        assertEquals("US", response.getCountryCode());
        assertEquals("United States", response.getCountryName());
        assertEquals("AT&T", response.getOperatorName());
        assertEquals("+1234567890", response.getMobileNumber());
        assertEquals(true, response.isValid());
    }

    @Test
    public void testValidateMobileNumber_InvalidNumber() throws CustomException {
        // Mock the MobileValidationDto response
        MobileValidationDto mockDto = new MobileValidationDto();
        mockDto.setCountry_code("US");
        mockDto.setCountry_name("United States");
        mockDto.setCarrier("AT&T");
        mockDto.setNumber("invalidNumber");
        mockDto.setValid(false);

        when(mobileValidationInterface.validateNumber(anyString(), anyString())).thenReturn(mockDto);

        // Call the service method and expect an exception
        InvalidMobileNumberException exception = assertThrows(InvalidMobileNumberException.class, () -> {
            mobileValidationServiceImpl.validateMobileNumber("invalidNumber");
        });

        // Verify the exception message
        assertEquals(messageService.getMessage("invalid.mobile.number",null,requestReaderComponent.getLanguage())
        		+ " : invalidNumber", exception.getMessage());
    }
}
