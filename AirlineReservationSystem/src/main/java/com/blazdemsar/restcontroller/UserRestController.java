package com.blazdemsar.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.domain.User;
import com.blazdemsar.service.UserService;

@CrossOrigin
@RestController
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/getAllUsers", method=RequestMethod.POST)
	public ResponseEntity<List<User>> getAllFlights() {
		
		System.out.println("In UserRestController.getAllUsers() ..... ");
		
		List<User> allUsers = userService.findAll();
		
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}
	
}
