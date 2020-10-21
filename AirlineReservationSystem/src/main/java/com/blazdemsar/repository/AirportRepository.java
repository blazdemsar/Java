package com.blazdemsar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazdemsar.domain.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {

}
