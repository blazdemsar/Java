package com.blazdemsar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.service.EmployeeService;

import java.util.List;
import com.blazdemsar.domain.Employee;

@RestController
public class EmployeeRestController {
  
	 @Autowired
	 EmployeeService employeeService;
	 
	 @RequestMapping(value="employees", method=RequestMethod.GET)
	 public ResponseEntity<List<Employee>> getAllEmployees(){
		 List<Employee> listOfEmployees = employeeService.findAll();
		 if(listOfEmployees.isEmpty()) {
			 return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		 }else {
			 return new ResponseEntity<List<Employee>>(listOfEmployees, HttpStatus.ACCEPTED);
		 }
		 
	 }
	 
	// http://localhost:8080/employee/9
	// @RequestMapping(value="employee/{id}") // if Request Method is not mentioned, then by default it is Http GET
	 @RequestMapping(value="employee/{id}", method=RequestMethod.GET)
	 public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id){
		 Employee employee = employeeService.findById(id);
		 if(employee == null) {
			// return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
			 return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		 }else {
			 return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
		 }
	 }
	 
	//http://localhost:8080/employee?id=9
		 @RequestMapping(value="employee", method=RequestMethod.GET)
		 public ResponseEntity<Employee> getEmployeeByIdUsingRequestParam(@RequestParam("id") int id){
			 Employee employee = employeeService.findById(id);
			 if(employee == null) {
				// return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
				 return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
			 }else {
				 return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
			 }
		 }
	 
		//http://localhost:8080/employee?id=9
		 @RequestMapping(value="deleteEmployee", method=RequestMethod.DELETE)
		 public ResponseEntity<?> deleteEmployeeById(@RequestParam("id") int id){
			
			 if(employeeService.existsById(id)) {
				 employeeService.deleteById(id);
				 return new ResponseEntity<String>("Employee record with id \""+id + "\" deleted", HttpStatus.ACCEPTED);
			 }else {
				 return new ResponseEntity<String>("No Employee with id \""+id + "\" exists.", HttpStatus.ACCEPTED);
			 }
			 
		 }
		 
}
