package com.blazdemsar.restcontroller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.mail.MessagingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.domain.Flight;
import com.blazdemsar.domain.Passenger;
import com.blazdemsar.domain.Reservation;
import com.blazdemsar.domain.User;
import com.blazdemsar.service.FlightService;
import com.blazdemsar.service.MailService;
import com.blazdemsar.service.PassengerService;
import com.blazdemsar.service.ReservationService;
import com.blazdemsar.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;

@CrossOrigin
@RestController
public class FlightRestController {

	@Autowired
	FlightService flightService;
	
	@Autowired
	PassengerService passengerService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailService mailService;
	
	@RequestMapping(value="/savePassenger", method=RequestMethod.POST)
	public ResponseEntity<?> savePassenger(@RequestBody Passenger passenger, Model model) {
		
		System.out.println("Inside of FlightRestController.savePassenger()....." + passenger);
		
		Passenger passFromDb = passengerService.save(passenger);
		System.out.println(passFromDb);
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject(mapper.writeValueAsString(passFromDb));
			jsonArray.put(jsonObject);
			
			JSONObject jsonResponseObject = new JSONObject();
			
			jsonResponseObject.put("currentPassenger", jsonArray);
			
			return new ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
			
		} catch (Exception e) {
            e.printStackTrace();
        }
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		return new ResponseEntity<String>("FlightRestController could not convert the passenger", HttpStatus.NOT_IMPLEMENTED);

	}
	
	@RequestMapping(value="/saveReservation", method=RequestMethod.POST)
	public ResponseEntity<?> saveReservation(@RequestBody String reservationDetails, Principal principal, Model model) throws DocumentException, IOException, MessagingException {
		
		System.out.println("Inside of FlightRestController.saveReservation()....." + reservationDetails);
		
		JSONObject jsonReservation = new JSONObject(reservationDetails);
		
		Reservation reservation = new Reservation();
		Passenger passenger = passengerService.findById(jsonReservation.getLong("passengerId"));
		Flight flight = flightService.findById(jsonReservation.getLong("flightId"));
		
		reservation.setPassenger(passenger);
		reservation.setFlight(flight);
		reservation.setCheckedIn(false);
		reservation.setNrOfBags(jsonReservation.getInt("nrOfBags"));
		reservation.setTicketFare(jsonReservation.getDouble("ticketFare"));
		reservation.setUsername(principal.getName());
				
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		Reservation resFromDb = reservationService.save(reservation);
		System.out.println(resFromDb);
		
		mailService.reservationConfirmationPdfCreator(principal.getName(), resFromDb.getPassenger().getEmail(), resFromDb);
		
		return new ResponseEntity<String>("FlightRestController successfully saved the reservation", HttpStatus.OK);

	}
	
	@RequestMapping(value="/getAllFlights", method=RequestMethod.POST)
	public ResponseEntity<List<Flight>> getAllFlights() {
		
		System.out.println("In FlightRestController.getAllFlights() ..... ");
		
		List<Flight> allFlights = flightService.findAll();
		
		return new ResponseEntity<List<Flight>>(allFlights, HttpStatus.OK);
	}
}
