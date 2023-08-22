package com.cpg.flightapi.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpg.flightapi.Mapper.FlightMapper;
import com.cpg.flightapi.dto.FlightDto;
import com.cpg.flightapi.entity.FlightDetails;
import com.cpg.flightapi.exception.FlightNotFoundException;
import com.cpg.flightapi.repository.FlightRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FlightService {

	private final FlightRepository flightRepository;

	private final FlightMapper flightMapper;

	public List<FlightDto> getAllFlights() {
		try {
			List<FlightDetails> flights = flightRepository.findAll();
			return flightMapper.tomapDto(flights);
		} catch (Exception e) {
			throw new FlightNotFoundException("An error occured");
		}
	}

	public List<FlightDto> getFlightsSortedByPrice() {
		try {
			List<FlightDetails> allFlights = flightRepository.findAll();
			return allFlights.stream().filter(Objects::nonNull).map(flightMapper::toDto).collect(Collectors.toList());

		} catch (Exception e) {
			throw new FlightNotFoundException("An error occured");
		}

	}

	public List<FlightDto> getFlightsSortedByDuration() {
		try {
			List<FlightDetails> flights = flightRepository.findAll();

			return flights.stream().sorted(Comparator.comparingLong(FlightDetails::getDurationInMinutes))
					.map(flightMapper::toDto).collect(Collectors.toList());

		} catch (Exception e) {
			throw new FlightNotFoundException("An error occured");
		}
	}

	public FlightDto getFlightById(Long id) {

		return flightRepository.findById(id).map(flightMapper::toDto)
				.orElseThrow(() -> new FlightNotFoundException("NO FLIGHT PRESENT WITH ID = " + id));
	}

	public List<FlightDto> getFlightsByOriginAndDestination(String origin, String destination) {

		try {
			List<FlightDetails> allFlights = flightRepository.findAll();
			return allFlights.stream()
					.filter(flight -> flight.getOrigin().equals(origin) && flight.getDestination().equals(destination))
					.map(flightMapper::toDto).collect(Collectors.toList());

		} catch (Exception e) {
			throw new FlightNotFoundException("An error occured");
		}
	}

	public FlightDto createFlight(FlightDto flightDto) {
		try {
			// to convert Dto to entity
			FlightDetails flightDetails = flightMapper.toEntity(flightDto);
			FlightDetails details = flightRepository.save(flightDetails);
			// created entity back to dto and return it
			return flightMapper.toDto(details);
		} catch (Exception e) {
			throw new FlightNotFoundException("Error for creating flight" + e);
		}
	}

	public FlightDetails updateFlight(Long id, FlightDetails flightDetails) {
		if (flightRepository.existsById(id)) {
			flightDetails.setId(id);
			return flightRepository.save(flightDetails);
		}
		return null;
	}

	public void deleteFlight(Long id) {
		flightRepository.deleteById(id);
	}
}