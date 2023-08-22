package com.cpg.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cpg.flightapi.entity.FlightDetails;
@Repository
public interface FlightRepository extends JpaRepository<FlightDetails,Long> {

}
