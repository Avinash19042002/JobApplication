package com.avinash.reviewms;

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

}
