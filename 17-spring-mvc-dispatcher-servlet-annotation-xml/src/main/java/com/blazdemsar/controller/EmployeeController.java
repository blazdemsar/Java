package com.blazdemsar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.domain.Employee;

@Controller
public class EmployeeController {
	
	// http://localhost:8081/17-spring-mvc-dispatcher-servlet-annotation-xml/employee?empId=1&name=Blaz&job=Programmer&salary=8000
	
	@RequestMapping("/employee")
	public String employee(Model model, @RequestParam int empId, @RequestParam String name, @RequestParam String job, @RequestParam int salary) {
		
		System.out.println("Inside of EmployeeController.employee!");
		
		Employee employee = new Employee(empId, name, job, salary);
		
		model.addAttribute("emp", employee);
		
		return "employee";
	}
}
