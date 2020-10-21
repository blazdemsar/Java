package com.blazdemsar.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.integration.AirlineRestClient;
import com.blazdemsar.integration.dto.Airline;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin
@RestController
public class AirlineController {
	
	@Autowired
	AirlineRestClient airlineRestClient;
	
	@RequestMapping(value="/getAllAirlines", method=RequestMethod.GET)
	public ResponseEntity<String> getAllAirlines() throws JSONException, JsonProcessingException {

		System.out.println("Request for airlines received .............");
		
		JSONObject airlines = airlineRestClient.getAllAirlines();

		return new ResponseEntity<String>(airlines.toString(), HttpStatus.OK);

	}

}
