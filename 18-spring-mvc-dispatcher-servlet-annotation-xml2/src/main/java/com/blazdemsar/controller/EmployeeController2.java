package com.blazdemsar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.blazdemsar.domain.Employee;

@Controller
public class EmployeeController2 {
	
	// http://localhost:8081/17-spring-mvc-dispatcher-servlet-annotation-xml/getEmployee/1/Domen/DBA/7000
	
	@RequestMapping("/getEmployee/{empId}/{name}/{job}/{salary}")
	public String employee(Model model, @PathVariable int empId, @PathVariable String name, @PathVariable String job, @PathVariable int salary) {
		
		System.out.println("Inside of EmployeeController.employee!");
		
		Employee employee = new Employee(empId, name, job, salary);
		
		model.addAttribute("emp", employee);
		
		return "employee";
	}
}
