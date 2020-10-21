package com.blazdemsar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.domain.Airline;
import com.blazdemsar.domain.User;
import com.blazdemsar.service.AirlineService;
import com.blazdemsar.service.UserService;

@Controller
public class AirlineController {
	
	@Autowired
	AirlineService airlineService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="airline")
	public String getAirlineForm(Airline airline, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("airlines", airlineService.findAll());
		
		return "airline";
	}
	
	@RequestMapping(value="/saveAirline")
	public String saveAirline(@ModelAttribute Airline airline, BindingResult br, Model model) {
		
		System.out.println("@AirlineController.saveAirline(...)........ airline: "+ airline);
		
		if (!br.hasErrors()) {
			
			Airline airlineFromDb = airlineService.save(airline);
			
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("airlines", airlineService.findAll());
		
		return "airline";
	}
	
	@RequestMapping(value = "/deleteAirline")
	public String deleteAirline(Airline airline, @RequestParam Long airlineId, Model model) {
		
		airlineService.deleteById(airlineId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("airlines", airlineService.findAll());
		
		return "airline";
	}
	
	@RequestMapping(value = "/updateAirline")
	public String updateAirline(Airline airline, @RequestParam Long airlineId, Model model) {
		
		Airline airlineFromDb = airlineService.findById(airlineId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("airline", airlineFromDb);
		model.addAttribute("airlines", airlineService.findAll());
		
		return "airline";
	}
}
