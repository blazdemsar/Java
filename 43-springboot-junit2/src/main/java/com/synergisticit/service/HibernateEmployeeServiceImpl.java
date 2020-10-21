package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.dao.EmployeeDao;
import com.synergisticit.domain.Employee;

@Service (value="hibernateEmployeeService")
public class HibernateEmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public Employee save(Employee employee) {
		System.out.println("HibernateEmployeeServiceImpl......using hibernate in dao layer to save employee object....");
		return employeeDao.save(employee);
	}

	@Override
	public Employee findById(int id) {
		System.out.println("HibernateEmployeeServiceImpl......using hibernate in dao layer to find an employee object by id....");
		return employeeDao.findById(id);
	}

	@Override
	public List<Employee> findAll() {
		System.out.println("HibernateEmployeeServiceImpl......using hibernate in dao layer to find all employees....");
		return employeeDao.findAll();
	}

	@Override
	public Employee updateById(int id) {
		System.out.println("HibernateEmployeeServiceImpl......using hibernate in dao layer to update employee by empId....");
		return employeeDao.updateById(id);
	}

	@Override
	public void deleteById(int id) {
		System.out.println("HibernateEmployeeServiceImpl......using hibernate in dao layer to delete employee object....");
		employeeDao.deleteById(id);

	}

	@Override
	public boolean existsById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insertUsingQueryAnnotation(int empId, String name, String designation, int salary, boolean insured) {
		employeeDao.insertUsingQueryAnnotation(empId, name, designation, salary, insured);

	}

	@Override
	public void updateSalaryByEmpIdUsingQueryAnnotation(int empId, int salary) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByIdUsingQueryAnnotation(int empId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee getById(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
