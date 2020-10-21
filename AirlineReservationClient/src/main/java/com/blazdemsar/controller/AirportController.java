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

import com.blazdemsar.integration.AirportRestClient;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin
@RestController
public class AirportController {
	
	@Autowired
	AirportRestClient airportRestClient;
	
	@RequestMapping(value="/getAllAirports", method=RequestMethod.GET)
	public ResponseEntity<String> getAllAirports() throws JSONException, JsonProcessingException {

		System.out.println("Request for airports received .............");
		
		JSONObject jsonResponseObject = airportRestClient.getAllAirports();

		return new ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);

	}

}
