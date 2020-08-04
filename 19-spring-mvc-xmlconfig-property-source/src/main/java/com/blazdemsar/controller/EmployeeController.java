package com.blazdemsar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.domain.Employee;
import com.blazdemsar.service.EmployeeService;

@Controller
public class EmployeeController {
	
	EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	// http://localhost:8081/17-spring-mvc-dispatcher-servlet-annotation-xml/employee?empId=1&name=Blaz&job=Programmer&salary=8000
	
	@RequestMapping("/employee")
	public String employee(Model model, @RequestParam int empId, @RequestParam String name, @RequestParam String job, @RequestParam int salary) {
		
		System.out.println("Inside of EmployeeController.employee...");
		
		Employee employee = new Employee(empId, name, job, salary);
		model.addAttribute("emp", employee);
		
		return "employee";
	}
	
	@RequestMapping("/employeeForm")
	public String employeeForm(Employee employee, Model model) {
		System.out.println("Inside of EmployeeController.employeeForm()...");
		model.addAttribute("employees", employeeService.findAll());
		return "employeeForm";
	}
	
	@RequestMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee, Model model) {
		System.out.println("Inside of EmployeeController.saveEmployee()...");
		model.addAttribute("employee", employee);
		employeeService.save(employee);
		model.addAttribute("employees", employeeService.findAll());
		return "employeeForm";
	}
}
