package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.domain.Airline;

public interface AirlineService {
	
	public Airline save(Airline airline);
	public Airline findById(Long airlineId);
	public List<Airline> findAll();
	public Airline updateById(Long airlineId);
	public void deleteById(Long airlineId);
	
}
