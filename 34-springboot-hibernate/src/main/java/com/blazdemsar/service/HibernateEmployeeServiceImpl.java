package com.blazdemsar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.dao.EmployeeDao;
import com.blazdemsar.domain.Employee;

@Service (value="hibernateEmployeeService")
public class HibernateEmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public Employee save(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	public Employee findById(int id) {
		return employeeDao.findById(id);
	}

	@Override
	public List<Employee> findAll() {
		System.out.println("HibernateEmployeeServiceImpl......................");
		return employeeDao.findAll();
	}

	@Override
	public Employee updateById(int id) {
		return employeeDao.updateById(id);
	}

	@Override
	public void deleteById(int id) {
		employeeDao.deleteById(id);
	}

	@Override
	public boolean existsById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insertUsingQueryAnnotation(int empId, String name, String job, int salary, boolean insured) {
		// TODO Auto-generated method stub

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

}
