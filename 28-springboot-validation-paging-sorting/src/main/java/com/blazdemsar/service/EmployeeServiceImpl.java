package com.blazdemsar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.Employee;
import com.blazdemsar.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
	
	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> optEmployee = employeeRepository.findById(id);
		if(optEmployee.isPresent()) {
			return optEmployee.get();
		}else{
		  return null;
		}
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateById(int id) {		
		return findById(id);
	}

	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);

	}

	@Override
	public boolean existsById(int id) {
		return employeeRepository.existsById(id);
	}

}
