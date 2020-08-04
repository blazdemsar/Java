package com.blazdemsar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.blazdemsar.domain.Employee;
import com.blazdemsar.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepo.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeRepo.findAll();
	}

	@Override
	public void deleteById(int empId) {
		// TODO Auto-generated method stub
		
		Optional<Employee> optEmp = employeeRepo.findById(empId);
		
		if(optEmp.isPresent()) {
			employeeRepo.deleteById(empId);
			System.out.println("Employee record was deleted for EMPID : " + empId);
		} else {
			System.out.println("Employee record does not exist.");
		}
	}

	@Override
	public Employee findById(int empId) {
		Optional<Employee> optEmp = employeeRepo.findById(empId);
		
		if(optEmp.isPresent()) {
			return optEmp.get();
		} else {
			return null;
		}
	}

	@Override
	public Employee updateById(int empId, String job, int salary) {
		
		Optional<Employee> optEmp = employeeRepo.findById(empId);
		if (optEmp.isPresent()) {
			Employee empFromDb = optEmp.get();
			empFromDb.setJob(job);
			empFromDb.setSalary(salary);
			return employeeRepo.save(empFromDb); 
		} else {
			return null;
		}

	}

}
