package com.cpg.flightapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flight Search API"))
public class FlightApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlightApiApplication.class, args);

	}

}
