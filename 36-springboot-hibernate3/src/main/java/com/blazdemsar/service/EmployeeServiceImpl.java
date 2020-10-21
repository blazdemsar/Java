package com.blazdemsar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.Employee;
import com.blazdemsar.repository.EmployeeRepository;

@Service (value="JpaRepoEmployeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
	
	@Override
	public Employee save(Employee employee) {
		System.out.println("Using EmployeeRepository to save the employee object.");
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
		System.out.println("Using EmployeeRepository to get all employee objects.");
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateById(int id) {
		System.out.println("Using EmployeeRepository to find the employee object.");
		return findById(id);
	}

	@Override
	public void deleteById(int id) {
		System.out.println("Using EmployeeRepository to delete the employee object.");
		employeeRepository.deleteById(id);

	}

	@Override
	public boolean existsById(int id) {
		System.out.println("Using EmployeeRepository to test whether an employee object exists.");
		return employeeRepository.existsById(id);
	}

	@Override
	public void insertUsingQueryAnnotation(int empId, String name, String job, int salary, boolean insured) {
		System.out.println("Using EmployeeRepository to save the employee object using Query annotation.");
		employeeRepository.insertUsingQueryAnnotation(empId, name, job, salary, insured);
		
	}

	@Override
	public void updateSalaryByEmpIdUsingQueryAnnotation(int empId, int salary) {
		System.out.println("Using EmployeeRepository to update the employee salary using Query annotation.");
		employeeRepository.updateSalaryByEmpIdUsingQueryAnnotation(empId, salary);
		
	}

	@Override
	public void deleteByIdUsingQueryAnnotation(int empId) {
		System.out.println("Using EmployeeRepository to delete the employee object using Query annotation.");
		employeeRepository.deleteByIdUsingQueryAnnotation(empId);
		
	}

	@Override
	public Employee getById(int empId) {	
		System.out.println("Using EmployeeRepository to get the employee object by empId.");
		return employeeRepository.getById(empId);
	}

}
