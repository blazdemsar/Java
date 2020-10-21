package com.blazdemsar.controller;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.integration.ReservationRestClient;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin
@RestController
public class ReservationController {
	
	@Autowired
	ReservationRestClient reservationRestClient;
	
	@RequestMapping(value="/getAllReservations", method=RequestMethod.POST)
	public ResponseEntity<String> getAllReservations() throws JSONException, JsonProcessingException {

		System.out.println("Request for reservations received .............");
		
		JSONObject jsonResponseObject = reservationRestClient.getAllReservations();

		return new ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);

	}

}
