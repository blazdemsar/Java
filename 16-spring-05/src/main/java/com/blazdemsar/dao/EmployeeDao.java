package com.blazdemsar.dao;

import java.util.List;

import org.springframework.orm.jpa.JpaTransactionManager;

import com.blazdemsar.domain.Employee;

public interface EmployeeDao {
	
	public Employee saveEmployee(Employee employee);
	public List<Employee> findAll();
	public void deleteById(int empId);
	public Employee findById(int empId);
	public Employee updateById(int empId, String job, int salary);
	
}
