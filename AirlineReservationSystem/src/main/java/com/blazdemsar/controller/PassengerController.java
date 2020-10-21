package com.blazdemsar.controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blazdemsar.domain.Passenger;
import com.blazdemsar.domain.User;
import com.blazdemsar.service.PassengerService;
import com.blazdemsar.service.UserService;

@Controller
public class PassengerController {
	
	@Autowired
	PassengerService passengerService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="passenger")
	public String passengerForm(Passenger passenger, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);

		model.addAttribute("passengers", passengerService.findAll());
		
		return "passenger";
		
	}
	
}
