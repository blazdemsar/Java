package com.blazdemsar.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "job", "employee.job.empty", "Designation is required." );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "employee.email.empty", "Email is required." );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "employee.mobile.empty", "mobile is required." );
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.addressLine1", "address.addressLine1", "Address Line1 is required." );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.addressLine2", "address.addressLine2", "Address Line2 is required." );

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "address.city", "City is required." );

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.state", "address.state", "State is required." );

		
		
		if(employee.getSalary()<3000  || employee.getSalary()>8000) {
			errors.rejectValue("salary", "employee.salary.value", "Salary should be between $3000 and $8000.");
		}
		
		System.out.println("====================printing the validation failures==========================");
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		javax.validation.Validator validator = validatorFactory.getValidator();
		
		Set<ConstraintViolation<Employee>> constraintViolations =  validator.validate(employee);
		
		System.out.println("********Size of ConstraintViolation: "+  constraintViolations.size() + " ************");
		
		for(ConstraintViolation<Employee> constraintViolation : constraintViolations) {
			System.out.println(constraintViolation.getMessage());
		}
	}
	
	

}
