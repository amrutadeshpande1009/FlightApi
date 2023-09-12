package com.cpg.flightapi.service;

import java.util.List;

import com.cpg.flightapi.dto.FlightDto;
import com.cpg.flightapi.entity.FlightDetails;

public interface FlightService {

	public List<FlightDto> getAllFlights();

	public List<FlightDto> getFlightsSortedByPrice();

	public List<FlightDto> getFlightsSortedByDuration();

	public FlightDto getFlightById(Long id);

	public List<FlightDto> getFlightsByOriginAndDestination(String origin, String destination);

	public FlightDto createFlight(FlightDto flightDto);

	public FlightDetails updateFlight(Long id, FlightDetails flightDetails);

	public void deleteFlight(Long id);

	

}
