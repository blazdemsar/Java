package com.blazdemsar.restcontroller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.domain.Role;
import com.blazdemsar.service.RoleService;

@CrossOrigin
@RestController
public class RoleRestController {
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value="/getAllRoles", method=RequestMethod.POST)
	public ResponseEntity<List<Role>> getAllRoles() {
		
		System.out.println("In RoleRestController.getAllRoles() ..... ");
		
		List<Role> allRoles = roleService.findAll();
		
		return new ResponseEntity<List<Role>>(allRoles, HttpStatus.OK);
	}
}
