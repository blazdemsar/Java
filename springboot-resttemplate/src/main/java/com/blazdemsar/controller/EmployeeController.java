package com.blazdemsar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.integration.EmployeeRestClient;
import com.blazdemsar.integration.dto.Employee;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeRestClient  employeeRestClient;
	
	@RequestMapping(value="employee")
	public ResponseEntity<?> findEmployeeById(@RequestParam int empId){		
		/*
		 * Employee employee = employeeRestClient.findEmployeeById(empId); if(employee
		 * !=null) { return new ResponseEntity<Employee> (employee, HttpStatus.FOUND);
		 * }else { return new ResponseEntity<String> (" employee " + empId +
		 * " is not there.", HttpStatus.FOUND); }
		 */
		
		return employeeRestClient.findEmployeeById2(empId);
	}
	
	@RequestMapping(value="findAllEmployees")
	public ResponseEntity<List<Employee>> findAllEmployees(){		
		return new ResponseEntity<List<Employee>>(employeeRestClient.findAll()  ,HttpStatus.FOUND);
	}
	
	@RequestMapping(value="/employeeForm")
	public String getEmployeeForm(@ModelAttribute Employee employee, Model model) {
		model.addAttribute("spokenLanguages", spokenLanguages());
		model.addAttribute("hobbies", hobbies());
		return "employeeForm";
	}
	
	@RequestMapping(value="/saveTheEmployee")
	public ResponseEntity<Employee> saveEmployee(@ModelAttribute Employee employee){
		// use employeeRestClient to save the employee
		
		return new ResponseEntity<Employee>(employeeRestClient.save(employee)  ,HttpStatus.CREATED);
	}
	
	public List<String>  spokenLanguages(){
		List<String>  spokenLanguages = new ArrayList<>();
		spokenLanguages.add("English");
		spokenLanguages.add("Spanish");
		spokenLanguages.add("French");
		spokenLanguages.add("German");
		spokenLanguages.add("Hindi");
		spokenLanguages.add("Arabic");
		spokenLanguages.add("Chinese");
		spokenLanguages.add("Others");
		return spokenLanguages;
	}
	
	public String[] hobbies() {
		String[] hobbies = {"Cricket", "FootBall", "Badminton", "Cycling", "Swimming", "Morning Walk"};
		return hobbies;
	}
	
	@RequestMapping(value="/updateEmployee")
	public ResponseEntity<Employee> updateEmployee(@RequestParam int id, @RequestParam String job, @RequestParam int salary) {
		
		return new ResponseEntity<Employee>(employeeRestClient.updateEmployeeById(id, job, salary), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/deleteEmployee")
	public ResponseEntity<String> deleteEmployee(@RequestParam int id) {
		
		return employeeRestClient.deleteEmployeeById(id);
	}

}
