package com.blazdemsar.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.domain.Flight;
import com.blazdemsar.domain.User;
import com.blazdemsar.service.FlightService;
import com.blazdemsar.service.UserService;

@Controller
public class BasicController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	FlightService flightService;
	
	@RequestMapping(value="login")
	public String login(@RequestParam(value="logout", required=false) String logout,
			@RequestParam(value="error", required=false) String error,
			HttpServletRequest request, HttpServletResponse response, Model model
			)
	
	{
		
		String message = null;
		System.out.println("@BasicController.login() ....");

		if(logout != null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication !=null ) {
				new SecurityContextLogoutHandler().logout(request, response, authentication);
			}
			message = "You have been logged out successfully.";
			return "login";
		}

		if(error != null) {
			message = "Your username and/or password do not match.";
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);

		model.addAttribute("message", message);
		return "login";

	}

	@GetMapping(value="accessDeniedPage")
	public String accessDenied(Model model) {	   
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		return "accessDenied";
	}

	@RequestMapping(value= {"/", "/home"})
	public String getHomePage(Flight flight, Model model) {
		
		System.out.println("@BasicController.getHomePage(...)........ ");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		//List<Flight> departingFlights = flightService.
		
		//model.addAttribute(attributeName, attributeValue);
		
		return "home";
	}
}
