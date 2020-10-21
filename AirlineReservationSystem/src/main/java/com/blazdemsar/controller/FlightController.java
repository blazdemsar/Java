package com.blazdemsar.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.domain.Airline;
import com.blazdemsar.domain.Airport;
import com.blazdemsar.domain.Flight;
import com.blazdemsar.domain.FlightStatus;
import com.blazdemsar.domain.Gender;
import com.blazdemsar.domain.IdentificationType;
import com.blazdemsar.domain.Passenger;
import com.blazdemsar.domain.Reservation;
import com.blazdemsar.domain.User;
import com.blazdemsar.service.AirlineService;
import com.blazdemsar.service.AirportService;
import com.blazdemsar.service.FlightService;
import com.blazdemsar.service.MailService;
import com.blazdemsar.service.PassengerService;
import com.blazdemsar.service.ReservationService;
import com.blazdemsar.service.UserService;
import com.itextpdf.text.DocumentException;

@Controller
public class FlightController {
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	AirlineService airlineService;
	
	@Autowired
	AirportService airportService;
	
	@Autowired
	PassengerService passengerService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailService mailService;
	
	@RequestMapping(value="flight")
	public String flightForm(Flight flight, Passenger passenger, Reservation reservation, Model model) {
		
		List<Airline> airlines = airlineService.findAll();
		List<Airport> airports = airportService.findAll();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("flights", flightService.findAll());
		model.addAttribute("allFlights", flightService.findAll());
		model.addAttribute("airlineSet", airlines);
		model.addAttribute("departureAirportSet", airports);
		model.addAttribute("arrivalAirportSet", airports);
		model.addAttribute("flightsAvailable", null);
		model.addAttribute("flightStatusValues", FlightStatus.values());
		model.addAttribute("genderValues", Gender.values());
		model.addAttribute("identificationTypeValues", IdentificationType.values());
		
		return "flight";
		
	}
	
	@PostMapping("saveFlight")
	public String saveFlight(@Valid @ModelAttribute Flight flight, Passenger passenger, Reservation reservation, BindingResult br, Model model) throws DocumentException, IOException, MessagingException {
		
		System.out.println("@FlightController.saveFlight()....flight: " + flight);
		
		Flight flightFromDb = flightService.findById(flight.getFlightId());

		if (!br.hasErrors()) {
			
			if (flightFromDb == null) {
				
				flightService.save(flight);
				
			} else {
				
				Flight updatedFlight = flightService.save(flight);
				
				List<Reservation> reservationsOnThisFlight = reservationService.findByFlightId(updatedFlight.getFlightId());
				
				for (Reservation r : reservationsOnThisFlight) {
					
					mailService.reservationUpdatePdfCreator(r.getUsername(), r.getPassenger().getEmail(), r);
					
				}
				
			}
			
			
			
		}
		
		List<Airline> airlines = airlineService.findAll();
		List<Airport> airports = airportService.findAll();
		
		model.addAttribute("allFlights", flightService.findAll());
		model.addAttribute("airlineSet", airlines);
		model.addAttribute("departureAirportSet", airports);
		model.addAttribute("arrivalAirportSet", airports);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("flights", flightService.findAll());
		model.addAttribute("flightStatusValues", FlightStatus.values());
		model.addAttribute("genderValues", Gender.values());
		model.addAttribute("identificationTypeValues", IdentificationType.values());

		return "flight";
	}
	
	@RequestMapping(value="getFlights")
	public String getFlights(@ModelAttribute Flight flight, Passenger passenger, Reservation reservation, BindingResult br, Model model) {
		
		System.out.println("@FlightController.getFlights()....flight: " + flight);
		
		Long departingAirportId;
		Long arrivalAirportId;
		LocalDateTime departDate;
		
		if (flight.getDepartureAirport() == null) {
			
			if (flight.getArrivalAirport() == null) {
				
				if (flight.getDepartureDate() == null) {
					
					LocalDateTime nextWeek = LocalDateTime.now().plusDays(7);
					flight.setDepartureDate(nextWeek);
					departDate = flight.getDepartureDate();
					
					model.addAttribute("flightsAvailable", flightService.findByDepartDateWeekFromToday(departDate));
					
				} else {
					
					model.addAttribute("flightsAvailable", flightService.findByDepartDate(flight.getDepartureDate()));
					
				}
				
			} else {
				
				if (flight.getDepartureDate() == null) {
					
					arrivalAirportId = flight.getArrivalAirport().getAirportId();
					model.addAttribute("flightsAvailable", flightService.findByArrivalAirport(arrivalAirportId));
					
				} else {
					
					arrivalAirportId = flight.getArrivalAirport().getAirportId();
					departDate = flight.getDepartureDate();
					model.addAttribute("flightsAvailable", flightService.findByArrivalAirportDepartDate(arrivalAirportId, departDate));
					
				}
				
			}
			
		} else {
			
			if (flight.getArrivalAirport() == null) {
				
				if (flight.getDepartureDate() == null) {
					
					departingAirportId = flight.getDepartureAirport().getAirportId();
					model.addAttribute("flightsAvailable", flightService.findByDepartingAirport(departingAirportId));
					
				} else {
					
					departingAirportId = flight.getDepartureAirport().getAirportId();
					departDate = flight.getDepartureDate();
					model.addAttribute("flightsAvailable", flightService.findByDepartingAirportDepartDate(departingAirportId, departDate));
					
				}
				
			} else {
				
				if (flight.getDepartureDate() == null) {
					
					departingAirportId = flight.getDepartureAirport().getAirportId();
					arrivalAirportId = flight.getArrivalAirport().getAirportId();
					model.addAttribute("flightsAvailable", flightService.findByDepartingArrivalAirport(departingAirportId, arrivalAirportId));
					
				} else {
					
					departingAirportId = flight.getDepartureAirport().getAirportId();
					arrivalAirportId = flight.getArrivalAirport().getAirportId();
					departDate = flight.getDepartureDate();
					model.addAttribute("flightsAvailable", flightService.findByDepartingArrivalAirportDepartDate(departingAirportId, arrivalAirportId, departDate));
					
				}
			}
		}
		
		System.out.println();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		model.addAttribute("flights", flightService.findAll());
		model.addAttribute("allFlights", flightService.findAll());
		model.addAttribute("airlineSet", airlineService.findAll());
		model.addAttribute("departureAirportSet", airportService.findAll());
		model.addAttribute("arrivalAirportSet", airportService.findAll());
		model.addAttribute("flightStatusValues", FlightStatus.values());
		model.addAttribute("genderValues", Gender.values());
		model.addAttribute("identificationTypeValues", IdentificationType.values());
		
		return "flight";
	}
	
	@RequestMapping(value = "/updateFlight")
	public String updateFlight(Flight flight, @RequestParam Long flightId, Passenger passenger, Reservation reservation, Model model) {
		
		Flight flightFromDb = flightService.findById(flightId);
		
		List<Airline> airlines = airlineService.findAll();
		List<Airport> airports = airportService.findAll();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("flight", flightFromDb);
		model.addAttribute("flights", flightService.findAll());
		model.addAttribute("allFlights", flightService.findAll());
		model.addAttribute("airlineSet", airlines);
		model.addAttribute("departureAirportSet", airports);
		model.addAttribute("arrivalAirportSet", airports);
		model.addAttribute("flightsAvailable", null);
		model.addAttribute("flightStatusValues", FlightStatus.values());
		model.addAttribute("genderValues", Gender.values());
		model.addAttribute("identificationTypeValues", IdentificationType.values());
		
		return "flight";
	}
	
}
