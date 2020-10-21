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

import com.blazdemsar.domain.Airport;
import com.blazdemsar.domain.Flight;
import com.blazdemsar.service.AirlineService;
import com.blazdemsar.service.AirportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class AirportRestController {
	
	@Autowired
	AirportService airportService;
	
	@RequestMapping(value="/getAllAirports", method=RequestMethod.GET)
	public ResponseEntity<List<Airport>> getAllAirports() {
		
		System.out.println("In AirportRestController.getAllAirports() ..... ");
		
		List<Airport> allAirports = airportService.findAll();
		
		return new ResponseEntity<List<Airport>>(allAirports, HttpStatus.OK);
	}
}
