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

import com.blazdemsar.integration.RoleRestClient;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin
@RestController
public class RoleController {
	
	@Autowired
	RoleRestClient roleRestClient;
	
	@RequestMapping(value="/getAllRoles", method=RequestMethod.POST)
	public ResponseEntity<String> getAllRoles() throws JSONException, JsonProcessingException {

		System.out.println("Request for roles received .............");
		
		JSONObject jsonResponseObject = roleRestClient.getAllRoles();

		return new ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);

	}

}
