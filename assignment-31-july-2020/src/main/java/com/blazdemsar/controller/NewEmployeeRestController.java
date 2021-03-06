package com.blazdemsar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.domain.Employee;
import com.blazdemsar.service.EmployeeService;

@RestController
public class NewEmployeeRestController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "employees", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		
		List<Employee> listOfEmployees = employeeService.findAll();
		
		if (listOfEmployees.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Employee>>(listOfEmployees, HttpStatus.ACCEPTED);
		}
	}
	
	// http://localhost:8080/employee/5
	@RequestMapping(value="employee/{id}", method=RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
		
		Employee e = employeeService.findById(id);
		
		if (e == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Employee>(e, HttpStatus.FOUND);
		}
	}
	
	// http://localhost:8080/employee?id=5
	@RequestMapping(value="employee", method=RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeByIdUsingRequestParameter(@RequestParam("id") int id) {
		
		Employee e = employeeService.findById(id);
		
		if (e == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Employee>(e, HttpStatus.FOUND);
		}
	}
	
	// http://localhost:8080/deleteEmployee?id=9
	@RequestMapping(value="deleteEmployee", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployeebyId(@RequestParam("id") int id) {
		
		if (employeeService.existsById(id)) {
			employeeService.deleteById(id);
			return new ResponseEntity<String>("Employee record with id \"" + id + "\" deleted", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("No Employee with id \"" + id + "\" exists.", HttpStatus.ACCEPTED);
		}
	}
	
	//@RequestMapping(value="createEmployee", method=RequestMethod.POST, consumes="application/xml", produces="application/xml")
	@RequestMapping(value="createEmployee", method=RequestMethod.POST, consumes="application/xml", produces="application/json")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		
		if (employeeService.existsById(employee.getEmpId())) {
			
			return new ResponseEntity<String>("Employee with id \"" + employee.getEmpId() + "\" already exists.",
					HttpStatus.FOUND);
		} else {
			
			Employee e = employeeService.save(employee);
			
			return new ResponseEntity<Employee>(e, HttpStatus.CREATED);
		}
	}
	
	// http://localhost:8080/updateEmployee/5?salary=77777&job=MechanicalEngineering
	@RequestMapping(value = "updateEmployee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTheEmployee(@PathVariable int id, @RequestParam int salary, @RequestParam String job) {
		
		Employee employeeToBeUpdated = employeeService.findById(id);
		
		if (employeeToBeUpdated != null) {
			
			employeeToBeUpdated.setSalary(salary);
			employeeToBeUpdated.setJob(job);
			Employee e = employeeService.save(employeeToBeUpdated);
			
			return new ResponseEntity<Employee>(e, HttpStatus.ACCEPTED);
		} else {
			
			return new ResponseEntity<String>("Employee with id " + id + " does not exist.", HttpStatus.ACCEPTED);
		}
	}
	
}
