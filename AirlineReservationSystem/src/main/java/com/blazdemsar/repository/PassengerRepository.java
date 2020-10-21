package com.blazdemsar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazdemsar.domain.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
