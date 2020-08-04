package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.domain.Employee;

public interface EmployeeService {
	
	public Employee save(Employee employee);
	public List<Employee> findAll();
}
