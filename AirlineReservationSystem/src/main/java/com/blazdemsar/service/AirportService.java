package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.domain.Airport;

public interface AirportService {
	
	public Airport save(Airport airport);
	public Airport findById(Long airportId);
	public List<Airport> findAll();
	public Airport updateById(Long airportId);
	public void deleteById(Long airportId);
	
}
