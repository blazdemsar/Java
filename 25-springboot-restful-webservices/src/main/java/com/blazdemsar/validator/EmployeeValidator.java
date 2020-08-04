package com.blazdemsar.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.blazdemsar.domain.Employee;

@Component
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Employee.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Employee employee = (Employee)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "employee.name.empty", "Name is required." );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "job", "employee.job.empty", "Job is required." );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "employee.email.empty", "Email is required." );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "employee.mobile.empty", "mobile is required." );
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.addressLine1", "address.addressLine1", "Address Line1 is required." );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.addressLine2", "address.addressLine2", "Address Line2 is required." );

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "address.city", "City is required." );

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.state", "address.state", "State is required." );

		
		
		if(employee.getSalary()<3000) {
			errors.rejectValue("salary", "employee.salary.value", "Salary should not be less than $3000.");
		}
	}

}
