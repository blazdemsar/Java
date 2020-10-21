package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.domain.Reservation;

public interface ReservationService {
	
	public Reservation save(Reservation reservation);
	public Reservation findById(Long ticketId);
	public List<Reservation> findAll();
	public Reservation updateById(Long ticketId);
	public void deleteById(Long ticketId);
	public List<Reservation> findByUsername(String username);
	public List<Reservation> findByFlightId(Long flightId);
	
}
