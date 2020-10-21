package com.blazdemsar.integration;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blazdemsar.integration.dto.Employee;

public interface EmployeeRestClient {
	public Employee findEmployeeById(int empId);
	
	public ResponseEntity<Employee> findEmployeeById2(int empId);
	
	public List<Employee> findAll();
	
	public Employee save(Employee e);
	
	public ResponseEntity<String> deleteEmployeeById( int id);
	
	public Employee updateEmployeeById(int id, String job, int salary);
	

}
