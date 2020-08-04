package com.blazdemsar.dao;

import java.util.List;

import com.blazdemsar.domain.Employee;

public interface EmployeeDao {
	
	public Employee save(Employee employee);
	public List<Employee> findAll();
}
