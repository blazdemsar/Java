package com.blazdemsar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.Airline;
import com.blazdemsar.repository.AirlineRepository;

@Service
public class AirlineServiceImpl implements AirlineService {
	
	@Autowired
	AirlineRepository airlineRepository;
	
	@Override
	public Airline save(Airline airline) {
		return airlineRepository.save(airline);
	}

	@Override
	public Airline findById(Long airlineId) {
		
		Optional<Airline> optAirline = airlineRepository.findById(airlineId);
		
		if(optAirline.isPresent()) {
			
			return optAirline.get();
			
		}
		
		return null;
	}

	@Override
	public List<Airline> findAll() {
		return airlineRepository.findAll();
	}

	@Override
	public Airline updateById(Long airlineId) {
		return findById(airlineId);
	}

	@Override
	public void deleteById(Long airlineId) {
		airlineRepository.deleteById(airlineId);
	}

}
