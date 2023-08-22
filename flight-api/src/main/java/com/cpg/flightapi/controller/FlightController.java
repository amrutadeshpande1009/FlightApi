package com.cpg.flightapi.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpg.flightapi.dto.FlightDto;
import com.cpg.flightapi.entity.FlightDetails;
import com.cpg.flightapi.exception.FlightNotFoundException;
import com.cpg.flightapi.service.FlightService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class FlightController {

	private final FlightService flightService;

	@GetMapping("/flights/sorted")
	public ResponseEntity<List<FlightDto>> getallFlights(@RequestParam(required = false) String sortBy,
			@RequestParam(required = false) Long id) {
		if (id != null) {
			// Get a single flight by ID
			try {
				FlightDto flight = flightService.getFlightById(id);
				return ResponseEntity.ok(flight != null ? List.of(flight) : Collections.emptyList());
			} catch (FlightNotFoundException ex) {
				return ResponseEntity.notFound().build();
			}
		}
		if ("duration".equalsIgnoreCase(sortBy)) {
			try {
				List<FlightDto> flights = flightService.getFlightsSortedByDuration();
				// Get flights sorted by duration
				return ResponseEntity.ok(flights);
			} catch (FlightNotFoundException ex) {
				return ResponseEntity.notFound().build();
			}
		}
		if ("price".equalsIgnoreCase(sortBy)) {
			// Get flights sorted by price (default if sortBy is not specified)
			try {
				List<FlightDto> flights = flightService.getFlightsSortedByPrice();
				return ResponseEntity.ok(flights);
			} catch (FlightNotFoundException ex) {
				return ResponseEntity.notFound().build();
			}
		}
		List<FlightDto> allFlightDetails = flightService.getAllFlights();
		return ResponseEntity.ok(allFlightDetails);

	}

	@GetMapping("/find-list")
	public ResponseEntity<List<FlightDto>> getFlightsByOriginAndDestination(@RequestParam String origin,
			@RequestParam String destination) {
		try {
			List<FlightDto> flights = flightService.getFlightsByOriginAndDestination(origin, destination);
			return ResponseEntity.ok(flights);
		} catch (FlightNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/add-flight")
	public ResponseEntity<FlightDto> createFlight(@Valid @RequestBody FlightDto flight) {
		try {
			FlightDto savedFlight = flightService.createFlight(flight);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
		} catch (FlightNotFoundException ex) {
			return ResponseEntity.internalServerError().build();
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<FlightDetails> updateFlight(@PathVariable Long id, @RequestBody FlightDetails flight) {
		try {
			FlightDetails updatedFlight = flightService.updateFlight(id, flight);
			if (updatedFlight != null) {
				return ResponseEntity.ok(updatedFlight);
			}
		} catch (FlightNotFoundException ex) {
			return ResponseEntity.internalServerError().build();
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFlightByID(@PathVariable Long id) {
		flightService.deleteFlight(id);
		return ResponseEntity.noContent().build();
	}
}
