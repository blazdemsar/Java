package com.blazdemsar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.dao.EmployeeDao;
import com.blazdemsar.domain.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;
	
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}

	@Override
	public Employee save(Employee employee) {
		System.out.println("Inside of EmployeeServiceImpl.save()....");
		return employeeDao.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		System.out.println("Inside of EmployeeServiceImpl.findAll()....");
		return employeeDao.findAll();
	}

	@Override
	public Employee findById(int empId) {
		System.out.println("Inside of EmployeeServiceImpl.findById()....");
		return employeeDao.findById(empId);
	}

	@Override
	public Employee updateById(int empId, String job, int salary) {
		System.out.println("Inside of EmployeeServiceImpl.updateById()....");
		return employeeDao.updateById(empId, job, salary);
	}

	@Override
	public void deleteById(int empId) {
		System.out.println("Inside of EmployeeServiceImpl.deleteById()....");
		employeeDao.deleteById(empId);
	}

}
