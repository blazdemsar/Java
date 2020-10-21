package com.blazdemsar.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorControllerImpl implements ErrorController {

	@Override
	public String getErrorPath() {
		
		return "<strong>Some Exception Occurred.</strong>";
	}
	
	@RequestMapping("/error")
	public ResponseEntity<String> handleError(HttpServletRequest request){
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		Integer statusCode = Integer.valueOf(status.toString());
		if(statusCode == HttpStatus.NOT_FOUND.value()) {
			return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
		
	}

}
