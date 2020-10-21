package com.blazdemsar.service;
import java.io.File;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	 @Autowired
	 JavaMailSender  javaMailSender;
	 // The email account that is used to send the emails should turn "Less secure app access' on your gmail account to "ON".
	/* 
	 public void sendEmail() {
		 SimpleMailMessage message = new SimpleMailMessage();
		 message.setTo("synergisfremont@gmail.com", "fremontsession@gmail.com"	);
		 message.setCc("dinesh@synergisticit.com");
		 message.setSubject("Test Email");
		 message.setText("Hi, " + "\n This is a test email." + "\n Thanks & Regards, "+ "\nDinesh");
		 javaMailSender.send(message);
	 }
	 */
	 
	 public SimpleMailMessage sendEmail() {
		 SimpleMailMessage message = new SimpleMailMessage();
		 message.setTo("synergisfremont@gmail.com", "fremontsession@gmail.com", "sit25199@gmail.com");
		 message.setCc("dinesh@synergisticit.com");
		 message.setSubject("Test Email");
		 message.setText("Hi, " + "\n This is a test email." + "\n Thanks & Regards, "+ "\nDinesh");
		 javaMailSender.send(message);
		 return message;
	 }
	 
	 public MimeMessage sendEmailWithAttachment() {
		 MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		 
		 String text = "This email has an attachment. Please open and see the contents of the attachment.";
		 
		 try {
			 MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			 String[] to = {"synergisfremont@gmail.com", "fremontsession@gmail.com", "sit25199@gmail.com"};
			 InternetAddress[] sendTo = new InternetAddress[to.length];
			 
			 for(int i=0; i<to.length; i++) {
				 sendTo[i] = new InternetAddress(to[i]);
			 }
			 mimeMessageHelper.setTo(sendTo);
			 mimeMessageHelper.setSubject("Test email with attachment");
			 mimeMessageHelper.setText(text);
			 
			 FileSystemResource  fileSystemResource = new FileSystemResource("C:\\Users\\Dinesh Mishra\\Desktop\\attachment.txt");
			 mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
			 mimeMessageHelper.addAttachment("logo", new File("C:\\Users\\Dinesh Mishra\\Desktop\\24-Jun-2020\\40-springboot-mail\\src\\main\\resources\\static\\spring-logo.png"));
			 javaMailSender.send(mimeMessage);
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		
		 return mimeMessage;
	 }
}
