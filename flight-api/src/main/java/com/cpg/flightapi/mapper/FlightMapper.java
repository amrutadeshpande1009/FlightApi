package com.cpg.flightapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.cpg.flightapi.dto.FlightDto;
import com.cpg.flightapi.entity.FlightDetails;

@Component
public class FlightMapper {
	public FlightDto toDto(FlightDetails allFlights) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(allFlights, FlightDto.class);

	}
	
	public FlightDetails toEntity(FlightDto flightDto)
	{ModelMapper mapper = new ModelMapper();
		return mapper.map(flightDto, FlightDetails.class);
		
	}

	public List<FlightDto> tomapDto(List<FlightDetails> allFlights) {
		return allFlights.stream().map(this::toDto).collect(Collectors.toList());

	}

}
