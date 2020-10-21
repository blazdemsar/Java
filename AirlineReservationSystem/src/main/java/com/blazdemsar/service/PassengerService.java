package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.domain.Passenger;

public interface PassengerService {
	
	public Passenger save(Passenger passenger);
	public Passenger findById(Long passengerId);
	public List<Passenger> findAll();
	public Passenger updateById(Long passengerId);
	public void deleteById(Long passengerId);
}
