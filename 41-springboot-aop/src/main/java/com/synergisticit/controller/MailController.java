package com.synergisticit.controller;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.service.MailService;

@Controller
public class MailController {
	
	@Autowired
	MailService mailService;
	
	@RequestMapping(value="sendEmail")
	public ResponseEntity<String> sendEmail(){
		/*
		mailService.sendEmail();
		return new ResponseEntity<String>("Email has been sent.", HttpStatus.OK);
		*/
		
		SimpleMailMessage message = mailService.sendEmail();
		String to ="";
		int i = 0;
		for(String str : message.getTo()) {
			i++;
			to = to+"<br>"+ i+". "+str;
			
		}
		return new ResponseEntity<String>("Email has been sent to:<br>" +to, HttpStatus.OK);
	}
	
	/*
	@GetMapping("sendEmailWithAttachment")
	public ResponseEntity<MimeMessage> sendEmailWithAttachment(){
		
		MimeMessage mimeMessage = mailService.sendEmailWithAttachment(); 
		
		return new ResponseEntity<MimeMessage>(mimeMessage, HttpStatus.OK);
	}
	*/
	
	@GetMapping("sendEmailWithAttachment")
	public ResponseEntity<String> sendEmailWithAttachment() throws MessagingException {
		
		MimeMessage mimeMessage = mailService.sendEmailWithAttachment(); 
		String to = null;
		int i = 0;
		for(Address address : mimeMessage.getRecipients(Message.RecipientType.TO)) {
			i++;
			to = to+"<br>" + i + ". "+ address.toString();
		}
		
		return new ResponseEntity<String>("Email with attachment was sent to following receipients:<br>" +to, HttpStatus.OK);
	}

}
