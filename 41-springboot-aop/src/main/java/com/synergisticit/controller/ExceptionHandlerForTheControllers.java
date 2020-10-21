package com.synergisticit.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.synergisticit.utilities.EmployeeNotFoundException;
import com.synergisticit.utilities.UserNotFoundException;

@ControllerAdvice // it is applicable to all Controllers, but it can be applied selectively also based on attributes such as basePackages.
public class ExceptionHandlerForTheControllers {
	
	@ExceptionHandler({
			SQLException.class, IOException.class, ArithmeticException.class, EmployeeNotFoundException.class,
			UserNotFoundException.class
	})
	public ResponseEntity<String> handleExceptions(Exception ex){
		// return new ResponseEntity<String>(ex.getLocalizedMessage() +", ---from ExceptionHandlerForTheControllers.", HttpStatus.OK);
		
		String strMessage = "<br>Please click on the following link to get to home page:<br> <a href=\"home\">Home</a>";
		String strEx = ex.getLocalizedMessage();
		
		return new ResponseEntity<String>(strEx+strMessage, HttpStatus.OK);
	}

}
