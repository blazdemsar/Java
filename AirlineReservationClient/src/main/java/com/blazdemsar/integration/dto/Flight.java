package com.blazdemsar.integration.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Flight {
	
	private Long flightId;
	
	private String flightNumber;
	
	private Airline airline;

	private Airport departureAirport;
	
	private Airport arrivalAirport;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private LocalDateTime departureDate;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private LocalDateTime arrivalDate;
	
	private List<Reservation> reservations = new ArrayList<>();
	
	private FlightStatus flightStatus;
	
	private int noSeats;
	
	private double price;
	
	public Flight () {
		super();
	}

	public Flight(Long flightId, String flightNumber, Airline airline, Airport departureAirport,
			Airport arrivalAirport, LocalDateTime departureDate, LocalDateTime arrivalDate, List<Reservation> reservations, FlightStatus flightStatus, int noSeats, double price) {
		super();
		this.flightId = flightId;
		this.flightNumber = flightNumber;
		this.airline = airline;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.reservations = reservations;
		this.flightStatus = flightStatus;
		this.noSeats = noSeats;
		this.price = price;
	}

	public Flight(Airport departureAirport, Airport arrivalAirport) {
		super();
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public FlightStatus getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(FlightStatus flightStatus) {
		this.flightStatus = flightStatus;
	}

	public int getNoSeats() {
		return noSeats;
	}

	public void setNoSeats(int noSeats) {
		this.noSeats = noSeats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
