package com.blazdemsar.dao;

import java.util.List;

import com.blazdemsar.domain.Employee;

public interface EmployeeDao {
	
	public Employee save(Employee employee);
	public Employee findById(int id);
	public List<Employee> findAll();
	public Employee updateById(int id);
	public void deleteById(int id);
	
	
}
