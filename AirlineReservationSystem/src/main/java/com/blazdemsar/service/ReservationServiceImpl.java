package com.blazdemsar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.Reservation;
import com.blazdemsar.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Override
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation findById(Long ticketId) {
		
		Optional<Reservation> optReservation = reservationRepository.findById(ticketId);
		
		if(optReservation.isPresent()) {
			
			return optReservation.get();
			
		}
		
		return null;
	}

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation updateById(Long ticketId) {
		return findById(ticketId);
	}

	@Override
	public void deleteById(Long ticketId) {
		reservationRepository.deleteById(ticketId);
	}

	@Override
	public List<Reservation> findByUsername(String username) {
		return reservationRepository.findByUsername(username);
	}

	@Override
	public List<Reservation> findByFlightId(Long flightId) {
		return reservationRepository.findByFlightId(flightId);
	}
	
	
	

}
