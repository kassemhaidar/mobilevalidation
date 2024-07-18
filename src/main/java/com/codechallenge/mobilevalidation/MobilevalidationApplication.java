package com.codechallenge.mobilevalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,RefreshAutoConfiguration.class})
@EnableFeignClients
public class MobilevalidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobilevalidationApplication.class, args);
	}

}
