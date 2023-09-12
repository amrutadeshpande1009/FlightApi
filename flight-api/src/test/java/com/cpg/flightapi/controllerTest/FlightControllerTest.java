package com.cpg.flightapi.controllerTest;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.cpg.flightapi.controller.FlightController;
import com.cpg.flightapi.dto.FlightDto;
import com.cpg.flightapi.service.FlightService;
import java.time.LocalTime;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightController.class)
public class FlightControllerTest {

	@MockBean
	private FlightService flightService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetFlightsByOriginAndDestination() throws Exception {
		// Mocking the data from the service
		FlightDto flightDetailsDtos = new FlightDto();
		flightDetailsDtos.setFlightNumber("ABC123");
		flightDetailsDtos.setOrigin("Origin1");
		flightDetailsDtos.setDestination("Destination1");
		flightDetailsDtos.setArrivalTime(LocalTime.of(10, 15));
		flightDetailsDtos.setDepartureTime(LocalTime.of(15, 10));
		flightDetailsDtos.setPrice(200.0);

		when(flightService.getFlightsByOriginAndDestination("Origin1", "Destination1"))
				.thenReturn(Collections.singletonList(flightDetailsDtos));

		// Perform the GET request
		mockMvc.perform(get("/api/find-list").param("origin", "Origin1").param("destination", "Destination1"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].flightNumber", is("ABC123")))
				.andExpect(jsonPath("$[0].origin", is("Origin1")))
				.andExpect(jsonPath("$[0].destination", is("Destination1")))
				.andExpect(jsonPath("$[0].price", is(200.00)));

	}

	@Test
	public void testGetAllFlightsSorted() throws Exception {
		// Mocking the data from the service
		FlightDto flightDetailsDtos = new FlightDto();
		flightDetailsDtos.setFlightNumber("ABC123");
		flightDetailsDtos.setOrigin("Origin1");
		flightDetailsDtos.setDestination("Destination1");
		flightDetailsDtos.setArrivalTime(LocalTime.of(10, 15));
		flightDetailsDtos.setDepartureTime(LocalTime.of(15, 10));
		flightDetailsDtos.setPrice(200.0);

		when(flightService.getFlightsSortedByPrice()).thenReturn(Collections.singletonList(flightDetailsDtos));
		when(flightService.getFlightsSortedByDuration()).thenReturn(Collections.singletonList(flightDetailsDtos));
		// Perform the GET request duration
		mockMvc.perform(get("/api/flights/sorted").param("sortBy", "duration")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].flightNumber", is("ABC123")));
		verify(flightService, times(1)).getFlightsSortedByDuration();
		// Perform the GET request price
		mockMvc.perform(get("/api/flights/sorted").param("sortBy", "price")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].flightNumber", is("ABC123")));
		verify(flightService, times(1)).getFlightsSortedByPrice();

	}

}
