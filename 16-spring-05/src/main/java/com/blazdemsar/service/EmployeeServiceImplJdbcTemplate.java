package com.blazdemsar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blazdemsar.dao.EmployeeDao;
import com.blazdemsar.dao.EmployeeDaoImplJdbcTemplate;
import com.blazdemsar.domain.Employee;

public class EmployeeServiceImplJdbcTemplate implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDao.saveEmployee(employee);
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeDao.findAll();
	}

	@Override
	public void deleteById(int empId) {
		employeeDao.deleteById(empId);
	}

	@Override
	public Employee findById(int empId) {
		// TODO Auto-generated method stub
		return employeeDao.findById(empId);
	}

	@Override
	public Employee updateById(int empId, String job, int salary) {
		// TODO Auto-generated method stub
		return employeeDao.updateById(empId, job, salary);
	}

}
