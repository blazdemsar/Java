package com.blazdemsar.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.blazdemsar.domain.Flight;

public interface FlightService {
	
	public Flight save(Flight flight);
	public Flight findById(Long flightId);
	public List<Flight> findAll();
	public Flight updateById(Long flightId);
	public void deleteById(Long flightId);
	public List<Flight> findByDepartingAirport(Long departingAirportId);
	public List<Flight> findByArrivalAirport(Long arrivalAirportId);
	public List<Flight> findByDepartDate(LocalDateTime departDate);
	public List<Flight> findByDepartDateWeekFromToday(LocalDateTime departDate);
	public List<Flight> findByDepartingArrivalAirport(Long departingAirportId, Long arrivalAirportId);
	public List<Flight> findByDepartingAirportDepartDate(Long departingAirportId, LocalDateTime departDate);
	public List<Flight> findByArrivalAirportDepartDate(Long arrivalAirportId, LocalDateTime departDate);
	public List<Flight> findByDepartingArrivalAirportDepartDate(Long departingAirportId, Long arrivalAirportId, LocalDateTime departDate);
	
}
