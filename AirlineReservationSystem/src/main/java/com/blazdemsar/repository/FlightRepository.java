package com.blazdemsar.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blazdemsar.domain.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	
	@Query(value="SELECT * FROM airline_db.flight WHERE departureAirport_airportId=:departingAirportId", nativeQuery=true)
	public List<Flight> findByDepartingAirport(Long departingAirportId);
	
	@Query(value="SELECT * FROM airline_db.flight WHERE arrivalAirport_airportId=:arrivalAirportId", nativeQuery=true)
	public List<Flight> findByArrivalAirport(Long arrivalAirportId);
	
	@Query(value="SELECT * FROM airline_db.flight WHERE DATE(departureDate)=DATE(:departDate)", nativeQuery=true)
	public List<Flight> findByDepartDate(LocalDateTime departDate);
	
	@Query(value="SELECT * FROM airline_db.flight WHERE DATE(departureDate)>DATE(:departDate)", nativeQuery=true)
	public List<Flight> findByDepartDateWeekFromToday(LocalDateTime departDate);
	
	@Query(value="SELECT * FROM airline_db.flight WHERE arrivalAirport_airportId=:arrivalAirportId AND departureAirport_airportId=:departingAirportId", nativeQuery=true)
	public List<Flight> findByDepartingArrivalAirport(Long departingAirportId, Long arrivalAirportId);
	
	@Query(value="SELECT * FROM airline_db.flight WHERE departureAirport_airportId=:departingAirportId AND DATE(departureDate)=DATE(:departDate)", nativeQuery=true)
	public List<Flight> findByDepartingAirportDepartDate(Long departingAirportId, LocalDateTime departDate);
	
	@Query(value="SELECT * FROM airline_db.flight WHERE arrivalAirport_airportId=:arrivalAirportId AND DATE(departureDate)=DATE(:departDate)", nativeQuery=true)
	public List<Flight> findByArrivalAirportDepartDate(Long arrivalAirportId, LocalDateTime departDate);
	
	@Query(value="SELECT * FROM airline_db.flight WHERE arrivalAirport_airportId=:arrivalAirportId AND departureAirport_airportId=:departingAirportId AND DATE(departureDate)=DATE(:departDate)", nativeQuery=true)
	public List<Flight> findByDepartingArrivalAirportDepartDate(Long departingAirportId, Long arrivalAirportId, LocalDateTime departDate);
	
}
