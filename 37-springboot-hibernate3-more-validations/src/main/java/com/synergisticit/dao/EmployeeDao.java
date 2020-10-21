package com.synergisticit.dao;

import java.util.List;

import com.synergisticit.domain.Employee;

public interface EmployeeDao {
	
	public Employee save(Employee employee);
	public Employee findById(int id);
	public List<Employee> findAll();
	public Employee updateById(int id);
	public void deleteById(int id);
	
	//13-Aug-2020
	 public void insertUsingQueryAnnotation(int empId, String name, String job, int salary, boolean insured);
	 public void updateSalaryByEmpIdUsingQueryAnnotation(int empId, int salary);
	 public void deleteByIdUsingQueryAnnotation(int empId);
}
