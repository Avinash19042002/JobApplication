package com.avinash.reviewms;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class ReviewmsApplication {

	public static void main(String[] args) {
		// Set timezone before Spring context loads
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(ReviewmsApplication.class, args);
	}
	@PostConstruct
	public void init() {
		// Also set after Spring context loads (belt and suspenders)
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
	}

}
