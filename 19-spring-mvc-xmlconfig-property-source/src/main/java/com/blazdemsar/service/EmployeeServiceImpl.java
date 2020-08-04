package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.dao.EmployeeDao;
import com.blazdemsar.domain.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeDao employeeDao;
	
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}
	
	@Override
	public Employee save(Employee employee) {
		
		System.out.println("Inside of EmployeeServiceImpl.save()...");
		return employeeDao.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
