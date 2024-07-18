package com.codechallenge.mobilevalidation;

import com.codechallenge.mobilevalidation.business.service.MessageService;
import com.codechallenge.mobilevalidation.business.service.impl.MobileValidationServiceImpl;
import com.codechallenge.mobilevalidation.component.RequestReaderComponent;
import com.codechallenge.mobilevalidation.controller.MobileValidationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Base64;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class MobileValidationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MobileValidationServiceImpl validationService;

    @InjectMocks
    private MobileValidationController validationController;

    private String basicAuthHeader;
	
	@Autowired
	MessageService messageService;
	@Autowired
	RequestReaderComponent requestReaderComponent;

    @BeforeEach
    public void setup() {
        // Set up basic auth header
        String username = "admin";
        String password = "password";
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        basicAuthHeader = "Basic " + new String(encodedAuth);
    }

    @Test
    public void testValidateMobileNumber_ValidNumber_Success() throws Exception {
        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/mobile/validate")
                .param("number", "+9613333333")
                .header("Authorization", basicAuthHeader)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.countryCode").value("LB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.countryName").value("Lebanon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operatorName").value("Mobile Interim Company 1 sal (MIC1)"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mobileNumber").value("9613333333"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valid").value(true));
    }

    @Test
    public void testValidateMobileNumber_InvalidNumber_Exception() throws Exception {
        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/mobile/validate")
                .param("number", "invalidNumber")
                .header("Authorization", basicAuthHeader)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(
                		messageService.getMessage("invalid.mobile.number",null,requestReaderComponent.getLanguage()) + " : invalidNumber"));
    }
}

