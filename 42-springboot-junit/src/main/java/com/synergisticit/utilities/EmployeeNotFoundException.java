package com.synergisticit.utilities;

public class EmployeeNotFoundException extends RuntimeException{
	
	public EmployeeNotFoundException(int id) {
		super("No employee with empId "+ id);
	}
	
	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
