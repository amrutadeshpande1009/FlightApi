package com.cpg.flightapi.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Number is mandatory")
	private String flightNumber;
	@NotBlank(message = "Origin is mandatory")
	private String origin;
	@NotBlank(message = "destination is mandatory")
	private String destination;

	private LocalTime departureTime;

	private LocalTime arrivalTime;

	private double price;

	public long getDurationInMinutes() {
		return java.time.Duration.between(departureTime, arrivalTime).toMinutes();
	}

}
