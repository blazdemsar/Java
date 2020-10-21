package com.blazdemsar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazdemsar.domain.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long>{

}
