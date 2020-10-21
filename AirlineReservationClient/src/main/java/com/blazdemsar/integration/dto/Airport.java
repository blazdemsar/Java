package com.blazdemsar.integration.dto;

import java.util.ArrayList;
import java.util.List;

public class Airport {
	
	private Long airportId;
	
	private String airportName;
	
	private String airportCode; // abbreviation
	
	private List<Flight> allFlights = new ArrayList<>();
	
	public Airport () {
		super();
	}

	public Airport(Long airportId, String airportName, String airportCode, List<Flight> allFlights) {
		super();
		this.airportId = airportId;
		this.airportName = airportName;
		this.airportCode = airportCode;
		this.allFlights = allFlights;
	}

	public Long getAirportId() {
		return airportId;
	}

	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public List<Flight> getAllFlights() {
		return allFlights;
	}

	public void setAllFlights(List<Flight> allFlights) {
		this.allFlights = allFlights;
	}
	
	
}
