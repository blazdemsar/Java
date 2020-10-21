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

import com.blazdemsar.domain.Airport;
import com.blazdemsar.domain.User;
import com.blazdemsar.service.AirportService;
import com.blazdemsar.service.UserService;

@Controller
public class AirportController {
	
	@Autowired
	AirportService airportService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="airport")
	public String getAirportForm(Airport airport, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("airports", airportService.findAll());
		
		return "airport";
	}
	
	@RequestMapping(value="/saveAirport")
	public String saveAirport(@ModelAttribute Airport airport, BindingResult br, Model model) {
		
		System.out.println("@AirportController.saveAirport(...)........ airport: "+ airport);
		
		if (!br.hasErrors()) {
			
			Airport airportFromDb = airportService.save(airport);
			
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("airports", airportService.findAll());
		
		return "airport";
	}
	
	@RequestMapping(value = "/deleteAirport")
	public String deleteAirport(Airport airport, @RequestParam Long airportId, Model model) {
		
		airportService.deleteById(airportId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("airports", airportService.findAll());
		
		return "airport";
	}
	
	@RequestMapping(value = "/updateAirport")
	public String updateAirport(Airport airport, @RequestParam Long airportId, Model model) {
		
		Airport airportFromDb = airportService.findById(airportId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("airport", airportFromDb);
		model.addAttribute("airports", airportService.findAll());
		
		return "airport";
	}
}
