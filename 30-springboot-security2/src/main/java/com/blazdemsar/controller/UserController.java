package com.blazdemsar.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.domain.Role;
import com.blazdemsar.domain.User;
import com.blazdemsar.service.RoleService;
import com.blazdemsar.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	
	@RequestMapping(value="createUser", method=RequestMethod.GET)
	public ResponseEntity<User> createUser(@RequestParam String name, @RequestParam String password, 
			@RequestParam String email){
		
		User user = new User(name, password, email);
		List<Role> rolesFromDb = roleService.findAll();
		
		Set<Role> roles = new HashSet<>();
		/*
		roles.add(rolesFromDb.get(0));
		roles.add(rolesFromDb.get(1));
		*/
		roles.add(rolesFromDb.get(2));
		roles.add(rolesFromDb.get(3));
		user.setRoles(roles);
		
		User userFromDb = userService.createUser(user);
		
		return new ResponseEntity<User>(userFromDb, HttpStatus.CREATED);
	}
	
	//@GetMapping("login")
	//public String
}
