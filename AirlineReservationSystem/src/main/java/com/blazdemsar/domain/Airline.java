package com.blazdemsar.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Airline {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long airlineId;
	
	private String airlineName;
	
	private String airlineCode; // airline abbreviation
	
	@JsonBackReference
	@OneToMany(mappedBy="airline")
	private List<Flight> airlineFlights;
	
	public Airline () {
		super();
	}

	public Airline(Long airlineId, String airlineName, String airlineCode, List<Flight> airlineFlights) {
		super();
		this.airlineId = airlineId;
		this.airlineName = airlineName;
		this.airlineCode = airlineCode;
		this.airlineFlights = airlineFlights;
	}

	public Long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public List<Flight> getAirlineFlights() {
		return airlineFlights;
	}

	public void setAirlineFlights(List<Flight> airlineFlights) {
		this.airlineFlights = airlineFlights;
	}
	
	
}
