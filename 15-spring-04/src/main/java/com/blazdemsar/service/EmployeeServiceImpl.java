package com.blazdemsar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blazdemsar.domain.Employee;
import com.blazdemsar.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeRepo.findAll();
	}

	@Override
	public void deleteById(int empId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee findById(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateById(int empid, String job, int salary) {
		// TODO Auto-generated method stub
		return null;
	}

}
