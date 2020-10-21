package com.blazdemsar.validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "job", "employee.job.empty", "Job is required." );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "employee.email.empty", "Email is required." );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "employee.mobile.empty", "mobile is required." );
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billing_Address.addressLine1", "billing_Address.addressLine1", "Billing Address Line1 is required." );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billing_Address.addressLine2", "billing_Address.addressLine2", "Billing Address Line2 is required." );

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billing_Address.city", "billing_Address.city", "Billing City is required." );

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billing_Address.state", "billing_Address.state", "Billing State is required." );

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipping_Address.addressLine1", "shipping_Address.addressLine1", "Shipping Address Line1 is required." );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipping_Address.addressLine2", "shipping_Address.addressLine2", "Shipping Address Line2 is required." );

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipping_Address.city", "shipping_Address.city", "Shipping City is required." );

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipping_Address.state", "shipping_Address.state", "Shipping State is required." );
		
		if(employee.getSalary()<3000  || employee.getSalary()>8000) {
			errors.rejectValue("salary", "employee.salary.value", "Salary should be between $3000 and $8000.");
		}
		
		if(employee.getHobbies() == null || employee.getHobbies().length <1 ) {
			errors.rejectValue("hobbies", "employee.hobbies.value", "Must select atleast one hobby.");
		}
		
		if(employee.getSpokenLanguages().isEmpty()) {
			errors.rejectValue("spokenLanguages", "employee.spokenLanguages.value", "Select spoken language.");
		}
		
		if(employee.getSkills().isEmpty()) {
			errors.rejectValue("skills", "employee.skills.value", "Select atleast one software skill.");
		}
		
		if(employee.getEmployeeType() == null) {
			errors.rejectValue("employeeType", "employee.employeeType.value", "Select employee type.");
		}
		
		if(employee.getDob() != null) {
			Date dobDate = employee.getDob();
			System.out.println("year: "+(dobDate.getYear()+1900) + ", month:"+(dobDate.getMonth() + 1) + ", date:"+dobDate.getDate());
			
			LocalDate dobLocalDate = LocalDate.of(dobDate.getYear()+1900, dobDate.getMonth() + 1, dobDate.getDate());
			LocalDate currentLocalDate = LocalDate.now();
			Period period = Period.between(dobLocalDate, currentLocalDate);
			if(period.getYears() < 21) {
				errors.rejectValue("dob", "employee.dob.value", "Age must not be less than 21 years.");
			}
		}
		
		if(employee.getNationality()== null) {
			errors.rejectValue("nationality", "employee.nationality.value", "Nationality is required, please do select it.");
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
