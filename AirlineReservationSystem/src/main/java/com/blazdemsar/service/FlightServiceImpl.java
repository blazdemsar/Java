package com.blazdemsar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.Flight;
import com.blazdemsar.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	FlightRepository flightRepository;
	
	@Override
	public Flight save(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public Flight findById(Long flightId) {
		
		Optional<Flight> optFlight = flightRepository.findById(flightId);
		
		if(optFlight.isPresent()) {
			
			return optFlight.get();
			
		}
		
		return null;
	}

	@Override
	public List<Flight> findAll() {
		return flightRepository.findAll();
	}

	@Override
	public Flight updateById(Long flightId) {
		return findById(flightId);
	}

	@Override
	public void deleteById(Long flightId) {
		flightRepository.deleteById(flightId);
	}

	@Override
	public List<Flight> findByDepartingAirport(Long departingAirportId) {
		return flightRepository.findByDepartingAirport(departingAirportId);
	}

	@Override
	public List<Flight> findByArrivalAirport(Long arrivalAirportId) {
		return flightRepository.findByArrivalAirport(arrivalAirportId);
	}

	@Override
	public List<Flight> findByDepartDate(LocalDateTime departDate) {
		return flightRepository.findByDepartDate(departDate);
	}
	
	@Override
	public List<Flight> findByDepartDateWeekFromToday(LocalDateTime departDate) {
		return flightRepository.findByDepartDateWeekFromToday(departDate);
	}

	@Override
	public List<Flight> findByDepartingArrivalAirport(Long departingAirportId, Long arrivalAirportId) {
		return flightRepository.findByDepartingArrivalAirport(departingAirportId, arrivalAirportId);
	}

	@Override
	public List<Flight> findByDepartingAirportDepartDate(Long departingAirportId, LocalDateTime departDate) {
		return flightRepository.findByDepartingAirportDepartDate(departingAirportId, departDate);
	}

	@Override
	public List<Flight> findByArrivalAirportDepartDate(Long arrivalAirportId, LocalDateTime departDate) {
		return flightRepository.findByArrivalAirportDepartDate(arrivalAirportId, departDate);
	}

	@Override
	public List<Flight> findByDepartingArrivalAirportDepartDate(Long departingAirportId, Long arrivalAirportId, LocalDateTime departDate) {
		return flightRepository.findByDepartingArrivalAirportDepartDate(departingAirportId, arrivalAirportId, departDate);
	}
	
	

}
