package com.blazdemsar.restcontroller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.blazdemsar.domain.Flight;
import com.blazdemsar.domain.Reservation;
import com.blazdemsar.service.ReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class ReservationRestController {
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping(value="/getAllReservations", method=RequestMethod.POST)
	public ResponseEntity<List<Reservation>> getAllReservations() {
		
		System.out.println("In ReservationRestController.getAllReservations() ..... ");
		
		List<Reservation> allReservations = reservationService.findAll();
		
		return new ResponseEntity<List<Reservation>>(allReservations, HttpStatus.OK);
	}
}
