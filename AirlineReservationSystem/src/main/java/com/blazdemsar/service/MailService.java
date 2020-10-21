package com.blazdemsar.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.Reservation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class MailService {

	@Autowired
	JavaMailSender  javaMailSender;

	//return MimeMessage
	public MimeMessage sendConfirmationMessageWithAttachment(String username, String emailTo, String pathToAttachment) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(emailTo);
		helper.setCc("synergisfremont@gmail.com");
		helper.setSubject("Travel Itinerary - Blazing Airlines Inc.");
		helper.setText("Hi " + username + "!\n\nThank you for choosing Blazing Airlines! We have attached your reservation details below. "+
				"Should you have any questions don't hesitate to contact our customer service team." + "\n\nThank you and have a great day, "+ "\nBlazing Airlines");

		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment("Travel_Itinerary.pdf", file);

		javaMailSender.send(message);

		return message;

	}

	public MimeMessage sendCancellationMessageWithAttachment(String username, String emailTo, String pathToAttachment) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(emailTo);
		helper.setCc("synergisfremont@gmail.com");
		helper.setSubject("Reservation Cancellation - Blazing Airlines Inc.");
		helper.setText("Hi " + username + "!\n\nWe are saddened to see you go! As we respect our customers, we have cancelled your flight and attached the confirmation below. "+
				"If you encountered any problems during your reservation process or you have any other inquiries, please contact our customer support team, so we can help you solve any issues you might have." + "\n\nThank you and have a great day, "+ "\nBlazing Airlines Inc.");

		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment("Cancellation_Information.pdf", file);

		javaMailSender.send(message);

		return message;

	}

	public MimeMessage sendUpdateMessageWithAttachment(String username, String emailTo, String pathToAttachment) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(emailTo);
		helper.setCc("synergisfremont@gmail.com");
		helper.setSubject("Updates to your reservation " + username + "! - Blazing Airlines Inc.");
		helper.setText("Hi " + username + "!\n\nYou are receiving this email, because there have been updates to your reservation! Please review the attached file for updates to your account/flight. We hope that you enjoy your adventure, and as always if you have any questions or concerns, please contact our customer support team, so we can proerly assist you." + "\n\nThank you and have a great day, "+ "\nBlazing Airlines Inc.");

		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment("Travel_Update_Confirmation.pdf", file);

		javaMailSender.send(message);

		return message;

	}

	// return MimeMessage
	public MimeMessage reservationConfirmationPdfCreator(String username, String emailTo, Reservation reservation) throws DocumentException, IOException, MessagingException {

		System.out.println("Inside of MailService.reservationConfirmationPdfCreator().........");

		String pathToAttachment = "D:/FlightPDFs/Confirmation/Reservation_Confirmation_id_"+reservation.getTicketId()+".pdf";

		Document document = new Document();

		PdfWriter.getInstance(document, new FileOutputStream(pathToAttachment));

		document.open();

		Font title = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
		Font subtitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
		Font paragraphs = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

		Paragraph ttl = new Paragraph("Thank you for choosing Blazing Airlines " + reservation.getPassenger().getFirstName() + "!", title);
		ttl.setSpacingAfter(10f);
		document.add(ttl);

		Paragraph flightInfo = new Paragraph("Please read through the details of this itinerary and report any mistakes.", subtitle);
		flightInfo.setSpacingAfter(10f);
		document.add(flightInfo);

		Paragraph passInfo = new Paragraph("Passenger Information", subtitle);
		passInfo.setSpacingAfter(5f);
		document.add(passInfo);

		Paragraph firstName = new Paragraph("First name: " + reservation.getPassenger().getFirstName(), paragraphs);
		Paragraph lastName = new Paragraph("Last name: " + reservation.getPassenger().getLastName(), paragraphs);
		Paragraph dob = new Paragraph("Date of Birth: " + reservation.getPassenger().getDob(), paragraphs);
		Paragraph travelDoc = new Paragraph("Travel Document: " + reservation.getPassenger().getPassport(), paragraphs);

		travelDoc.setSpacingAfter(10f);

		document.add(firstName);
		document.add(lastName);
		document.add(dob);
		document.add(travelDoc);

		Paragraph forFlight = new Paragraph("Flight Details:", paragraphs);
		forFlight.setSpacingAfter(5f);
		document.add(forFlight);

		Paragraph airline = new Paragraph("Operating Airline: " + reservation.getFlight().getAirline().getAirlineName(), paragraphs);
		Paragraph flNumber = new Paragraph("Flight Number: " + reservation.getFlight().getFlightNumber(), paragraphs);
		Paragraph from = new Paragraph("Trip: " + reservation.getFlight().getDepartureAirport().getAirportCode() + " -> " + reservation.getFlight().getArrivalAirport().getAirportCode(), paragraphs);
		Paragraph depart = new Paragraph("Depart: " + reservation.getFlight().getDepartureDate().getMonth() + "/" + reservation.getFlight().getDepartureDate().getDayOfMonth() + "/" + reservation.getFlight().getDepartureDate().getYear() + " at " + reservation.getFlight().getDepartureDate().getHour() + ":" + reservation.getFlight().getDepartureDate().getMinute(), paragraphs);
		Paragraph arrive = new Paragraph("Arrive: " + reservation.getFlight().getArrivalDate().getMonth() + "/" + reservation.getFlight().getArrivalDate().getDayOfMonth() + "/" + reservation.getFlight().getArrivalDate().getYear() + " at " + reservation.getFlight().getArrivalDate().getHour() + ":" + reservation.getFlight().getArrivalDate().getMinute(), paragraphs);
		Paragraph status = new Paragraph("Flight Status: " + reservation.getFlight().getFlightStatus(), paragraphs);
		status.setSpacingAfter(10f);

		document.add(airline);
		document.add(flNumber);
		document.add(from);
		document.add(depart);
		document.add(arrive);
		document.add(status);

		Paragraph checkIn = new Paragraph("As this is only the confirmation of your reservation with us, you have not been checked in yet. In order to do so, please log into your account, navigate to your reservations and press the 'Check In' button. Upon successfully checking in you will receive another email.", paragraphs);
		checkIn.setSpacingAfter(15f);

		document.add(checkIn);

		Paragraph contact = new Paragraph("Contact Information on File: " + reservation.getPassenger().getEmail() + " / " + reservation.getPassenger().getPhoneNumber(), paragraphs);
		contact.setSpacingAfter(10f);
		document.add(contact);

		Paragraph totalPrice = new Paragraph("Total Price: $" + reservation.getTicketFare(), paragraphs);
		totalPrice.setSpacingAfter(20f);
		document.add(totalPrice);

		Paragraph thankYou = new Paragraph("Thank you again for choosing to travel with us. Have a great trip!", paragraphs);
		Paragraph company = new Paragraph("Blazing Airlines Inc.", paragraphs);
		thankYou.setSpacingAfter(10f);
		document.add(thankYou);
		document.add(company);

		document.close();
		System.out.println("Confirmation PDF created!");

		MimeMessage message = sendConfirmationMessageWithAttachment(username, emailTo, pathToAttachment);

		return message;

	}

	public MimeMessage reservationCancellationPdfCreator(String username, String emailTo, Reservation reservation) throws DocumentException, IOException, MessagingException {

		System.out.println("Inside of MailService.reservationCancellationPdfCreator().........");

		String pathToAttachment = "D:/FlightPDFs/Cancellation/Reservation_Cancellation_id_"+reservation.getTicketId()+".pdf";

		Document document = new Document();

		PdfWriter.getInstance(document, new FileOutputStream(pathToAttachment));

		document.open();

		Font title = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
		Font subtitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
		Font paragraphs = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

		Paragraph ttl = new Paragraph("This is your travel cancellation notice " + reservation.getPassenger().getFirstName() + "!", title);
		ttl.setSpacingAfter(15f);
		document.add(ttl);

		Paragraph sorry = new Paragraph("We are deeply sorry to see you go!", subtitle);
		sorry.setSpacingAfter(10f);
		document.add(sorry);

		Paragraph cancel = new Paragraph("We will make sure that your reservation cancellation is handled with care. If you encountered any problems that made you cancel the reservation, please don't hesitate to contact our support team so we can help in the future.", paragraphs);
		cancel.setSpacingAfter(10f);
		document.add(cancel);

		Paragraph passInfo = new Paragraph("Passenger Information", subtitle);
		passInfo.setSpacingAfter(5f);
		document.add(passInfo);

		Paragraph fullName = new Paragraph("Name on File: " + reservation.getPassenger().getLastName() + "," + reservation.getPassenger().getFirstName(), paragraphs);
		Paragraph dob = new Paragraph("Date of Birth: " + reservation.getPassenger().getDob(), paragraphs);	
		dob.setSpacingAfter(10f);

		document.add(fullName);
		document.add(dob);

		Paragraph forFlight = new Paragraph("Flight Details:", paragraphs);
		forFlight.setSpacingAfter(5f);
		document.add(forFlight);

		Paragraph airline = new Paragraph("Operating Airline: " + reservation.getFlight().getAirline().getAirlineName(), paragraphs);
		Paragraph flNumber = new Paragraph("Flight Number: " + reservation.getFlight().getFlightNumber(), paragraphs);
		Paragraph from = new Paragraph("Trip: " + reservation.getFlight().getDepartureAirport().getAirportCode() + " -> " + reservation.getFlight().getArrivalAirport().getAirportCode(), paragraphs);
		Paragraph depart = new Paragraph("Depart: " + reservation.getFlight().getDepartureDate().getMonth() + "/" + reservation.getFlight().getDepartureDate().getDayOfMonth() + "/" + reservation.getFlight().getDepartureDate().getYear() + " at " + reservation.getFlight().getDepartureDate().getHour() + ":" + reservation.getFlight().getDepartureDate().getMinute(), paragraphs);
		Paragraph arrive = new Paragraph("Arrive: " + reservation.getFlight().getArrivalDate().getMonth() + "/" + reservation.getFlight().getArrivalDate().getDayOfMonth() + "/" + reservation.getFlight().getArrivalDate().getYear() + " at " + reservation.getFlight().getArrivalDate().getHour() + ":" + reservation.getFlight().getArrivalDate().getMinute(), paragraphs);
		arrive.setSpacingAfter(10f);

		document.add(airline);
		document.add(flNumber);
		document.add(from);
		document.add(depart);
		document.add(arrive);

		Paragraph totalPrice = new Paragraph("Total Price: $" + reservation.getTicketFare(), paragraphs);
		totalPrice.setSpacingAfter(15f);
		document.add(totalPrice);

		Paragraph payment = new Paragraph("The flight cost will be refunded to the payment method provided at the time of reservation according to our refund policy. Please allow up to 30 days to receive your money back.", paragraphs);
		payment.setSpacingAfter(20f);
		document.add(payment);

		Paragraph thankYou = new Paragraph("Thank you for letting us serve you to the best of our abilities. Have a great day!", paragraphs);
		Paragraph company = new Paragraph("Blazing Airlines Inc.", paragraphs);
		thankYou.setSpacingAfter(10f);
		document.add(thankYou);
		document.add(company);

		document.close();
		System.out.println("Confirmation PDF created!");

		MimeMessage message = sendCancellationMessageWithAttachment(username, emailTo, pathToAttachment);

		return message;

	}
	
	public MimeMessage reservationUpdatePdfCreator(String username, String emailTo, Reservation reservation) throws DocumentException, IOException, MessagingException {
		
		System.out.println("Inside of MailService.reservationUpdatePdfCreator().........");
		
		String pathToAttachment = "D:/FlightPDFs/Update/Reservation_Update_id_"+reservation.getTicketId()+".pdf";
		
		Document document = new Document();
		
		PdfWriter.getInstance(document, new FileOutputStream(pathToAttachment));
				
		document.open();
		
		Font title = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
		Font subtitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
		Font paragraphs = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
		
		Paragraph ttl = new Paragraph("Hoo-ray, travelling the world is on the horizon " + reservation.getPassenger().getFirstName() + "!", title);
		ttl.setSpacingAfter(10f);
		document.add(ttl);
		
		Paragraph flightInfo = new Paragraph("This file serves as an update to your current reservation confirmation! Find the updated details below.", subtitle);
		flightInfo.setSpacingAfter(10f);
		document.add(flightInfo);
		
		Paragraph passInfo = new Paragraph("Passenger Information", subtitle);
		passInfo.setSpacingAfter(5f);
		document.add(passInfo);
		
		Paragraph firstName = new Paragraph("First name: " + reservation.getPassenger().getFirstName(), paragraphs);
		Paragraph lastName = new Paragraph("Last name: " + reservation.getPassenger().getLastName(), paragraphs);
		Paragraph gender = new Paragraph("Gender: " + reservation.getPassenger().getGender(), paragraphs);
		Paragraph dob = new Paragraph("Date of Birth: " + reservation.getPassenger().getDob(), paragraphs);
		Paragraph travelDoc = new Paragraph("Travel Document: " + reservation.getPassenger().getPassport(), paragraphs);
		
		travelDoc.setSpacingAfter(10f);
		
		document.add(firstName);
		document.add(lastName);
		document.add(gender);
		document.add(dob);
		document.add(travelDoc);
		
		Paragraph forFlight = new Paragraph("Flight Details:", paragraphs);
		forFlight.setSpacingAfter(5f);
		document.add(forFlight);
		
		Paragraph airline = new Paragraph("Operating Airline: " + reservation.getFlight().getAirline().getAirlineName(), paragraphs);
		Paragraph flNumber = new Paragraph("Flight Number: " + reservation.getFlight().getFlightNumber(), paragraphs);
		Paragraph from = new Paragraph("Trip: " + reservation.getFlight().getDepartureAirport().getAirportCode() + " -> " + reservation.getFlight().getArrivalAirport().getAirportCode(), paragraphs);
		Paragraph depart = new Paragraph("Depart: " + reservation.getFlight().getDepartureDate().getMonth() + "/" + reservation.getFlight().getDepartureDate().getDayOfMonth() + "/" + reservation.getFlight().getDepartureDate().getYear() + " at " + reservation.getFlight().getDepartureDate().getHour() + ":" + reservation.getFlight().getDepartureDate().getMinute(), paragraphs);
		Paragraph arrive = new Paragraph("Arrive: " + reservation.getFlight().getArrivalDate().getMonth() + "/" + reservation.getFlight().getArrivalDate().getDayOfMonth() + "/" + reservation.getFlight().getArrivalDate().getYear() + " at " + reservation.getFlight().getArrivalDate().getHour() + ":" + reservation.getFlight().getArrivalDate().getMinute(), paragraphs);
		Paragraph status = new Paragraph("Flight Status: " + reservation.getFlight().getFlightStatus(), paragraphs);
		status.setSpacingAfter(10f);
		
		document.add(airline);
		document.add(flNumber);
		document.add(from);
		document.add(depart);
		document.add(arrive);
		document.add(status);
		
		Paragraph checkIn;
		if (reservation.getCheckedIn()) {
			checkIn = new Paragraph("Check In Status: No further action required", paragraphs);
		} else {
			checkIn = new Paragraph("Check In Status: Not checked-in yet, please login to your account -> Reservations -> Check In", paragraphs);
		}
		 
		checkIn.setSpacingAfter(15f);
		document.add(checkIn);
		
		Paragraph contact = new Paragraph("Contact Information on File: " + reservation.getPassenger().getEmail() + " / " + reservation.getPassenger().getPhoneNumber(), paragraphs);
		contact.setSpacingAfter(10f);
		document.add(contact);
		
		Paragraph totalPrice = new Paragraph("Total Price: $" + reservation.getTicketFare(), paragraphs);
		totalPrice.setSpacingAfter(20f);
		document.add(totalPrice);
		
		Paragraph thankYou = new Paragraph("Thank you again for choosing to travel with us. Have a great trip!", paragraphs);
		Paragraph company = new Paragraph("Blazing Airlines Inc.", paragraphs);
		thankYou.setSpacingAfter(10f);
		document.add(thankYou);
		document.add(company);
		
		document.close();
		System.out.println("Confirmation PDF created!");
		
		MimeMessage message = sendUpdateMessageWithAttachment(username, emailTo, pathToAttachment);
		
		return message;
		
	}
}
