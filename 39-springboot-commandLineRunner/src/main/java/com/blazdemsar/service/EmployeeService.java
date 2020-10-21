package com.blazdemsar.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.blazdemsar.domain.Employee;

public interface EmployeeService {
	public Employee save(Employee employee);
	public Employee findById(int id);
	public List<Employee> findAll();
	public Employee updateById(int id);
	public void deleteById(int id);
	
	public boolean existsById(int id);
	
	//05-Aug-2020
	 public void insertUsingQueryAnnotation(int empId, String name, String job, int salary, boolean insured);
	 public void updateSalaryByEmpIdUsingQueryAnnotation(int empId, int salary);
	 public void deleteByIdUsingQueryAnnotation(int empId);
	 public Employee getById(int empId);
	 
	 //18-Aug-2020
	 List<Employee> findByName(String name);
}