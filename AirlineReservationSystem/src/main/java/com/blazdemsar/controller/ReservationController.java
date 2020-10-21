package com.blazdemsar.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

import com.blazdemsar.domain.Reservation;
import com.blazdemsar.domain.User;
import com.blazdemsar.service.MailService;
import com.blazdemsar.service.ReservationService;
import com.blazdemsar.service.UserService;
import com.itextpdf.text.DocumentException;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailService mailService;

	@RequestMapping(value="reservation")
	public String reservationForm(Reservation reservation, Model model, Principal principal) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("yourReservations", reservationService.findByUsername(principal.getName()));
		model.addAttribute("allReservations", reservationService.findAll());

		return "reservation";

	}


	@RequestMapping("updateReservation")
	public String updateReservation(@RequestParam Long ticketId, Model model, Principal principal) throws DocumentException, IOException, MessagingException{

		System.out.println("@ReservationController.updateReservation().... ticketId: " + ticketId);

		Reservation resFromDb = reservationService.findById(ticketId);	
		resFromDb.setCheckedIn(true);	
		reservationService.save(resFromDb);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		mailService.reservationUpdatePdfCreator(principal.getName(), resFromDb.getPassenger().getEmail(), resFromDb);

		model.addAttribute("yourReservations", reservationService.findByUsername(principal.getName()));
		model.addAttribute("allReservations", reservationService.findAll());

		return "reservation";
		
	}
	
	@RequestMapping("cancelReservation")
	public String cancelReservation(@RequestParam Long ticketId, Model model, Principal principal) throws DocumentException, IOException, MessagingException {

		System.out.println("@ReservationController.cancelReservation().... ticketId: " + ticketId);
		
		Reservation resFromDb = reservationService.findById(ticketId);
		reservationService.deleteById(ticketId);
		
		mailService.reservationCancellationPdfCreator(principal.getName(), resFromDb.getPassenger().getEmail(), resFromDb);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);

		model.addAttribute("yourReservations", reservationService.findByUsername(principal.getName()));
		model.addAttribute("allReservations", reservationService.findAll());

		return "reservation";
		
	}

}

