package com.cardinalhealth.chh.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main Spring Boot application for accessing DB calls.
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.cardinalhealth.chh")
public class ProviderServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ProviderServiceApplication.class, args);
	}
}
