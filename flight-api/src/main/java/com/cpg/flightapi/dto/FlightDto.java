package com.cpg.flightapi.dto;

import java.time.LocalTime;

import lombok.Data;

@Data
public class FlightDto {

	private Long id;

	private String flightNumber;

	private String origin;

	private String destination;

	private LocalTime departureTime;

	private LocalTime arrivalTime;

	private double price;

}
