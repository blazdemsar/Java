package com.blazdemsar.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blazdemsar.domain.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session;
	
	@Override
	public Employee save(Employee employee) {
		
		try {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(employee);
		session.getTransaction().commit();
		System.out.println("The record for employee "+ employee.getEmpId() + " has been saved.");
		
		}catch(Exception ex) {
			System.out.println("Problem in saving the employee record....");
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return employee;
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll() {
		System.out.println("EmployeeDaoImpl......................");
		List<Employee> employees = new ArrayList<>();
		try(Session session = sessionFactory.openSession();){
			session.beginTransaction();
			employees = session.createQuery("from Employee").list();
			for(Employee e : employees) {
				System.out.println(e.getName() +", "+e.getJob() + ", "+ e.getSalary());
			}
		}
		return employees;
	}

	@Override
	public Employee updateById(int id) {
		Employee e = null;
		
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			e = session.get(Employee.class, id);
			session.getTransaction().commit();
		}
		return e;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
