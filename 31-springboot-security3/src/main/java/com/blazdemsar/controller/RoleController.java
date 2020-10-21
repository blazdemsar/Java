package com.blazdemsar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.domain.Role;
import com.blazdemsar.service.RoleService;

@Controller
public class RoleController {
	
	@Autowired
	RoleService roleService;	
	
	@RequestMapping(value="createRole", method=RequestMethod.GET)
	public ResponseEntity<Role> createRole(@RequestParam String name){
		
		Role role = roleService.createRole(new Role(name));
		return new ResponseEntity<Role>(role, HttpStatus.ACCEPTED);
	}
	
}
