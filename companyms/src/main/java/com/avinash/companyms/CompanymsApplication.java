package com.avinash.companyms;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

@EnableFeignClients
@SpringBootApplication
public class CompanymsApplication {

	public static void main(String[] args) {
		// Set timezone before Spring context loads
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(CompanymsApplication.class, args);
	}
	@PostConstruct
	public void init() {
		// Also set after Spring context loads (belt and suspenders)
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
	}
}
