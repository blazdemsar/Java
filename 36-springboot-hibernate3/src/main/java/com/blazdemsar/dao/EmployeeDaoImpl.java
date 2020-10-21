package com.blazdemsar.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
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
		 Employee empfromDb = findById(employee.getEmpId());
		
		try {
		session = sessionFactory.openSession();
		session.beginTransaction();
		if(empfromDb != null) {
			session.saveOrUpdate(employee);
			
		}else {
			session.save(employee);
		}
	
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
		Employee employee = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			employee = session.get(Employee.class, id);
			session.getTransaction().commit();		    
		}catch(Exception e) {
			System.out.println("Problem in retriving the employee record.");
			e.printStackTrace();
		}finally {
			session.close();
		}
		return employee;
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
		try(Session session = sessionFactory.openSession()){
			session.beginTransaction();
			e = session.get(Employee.class, id);
			session.getTransaction().commit();
		}
		return e;
	}

	@Override
	public void deleteById(int id) {
		
		try(Session session = sessionFactory.openSession()){
			session.beginTransaction();
			Employee e = session.get(Employee.class, id);
			session.delete(e);
			session.getTransaction().commit();
		}
	}

	@Override
	public void insertUsingQueryAnnotation(int empId, String name, String job, int salary, boolean insured) {
		try(Session session = sessionFactory.openSession();){
			session.beginTransaction();
			String sqlString = "insert into employee(empId, name, job, salary, insured) "+
				    " values(:empId, :name, :job, :salary, :insured)";
			NativeQuery nq = session.createNativeQuery(sqlString);
		
			nq.setParameter("empId", empId);
			nq.setParameter("name", name);
			nq.setParameter("job", job);
			nq.setParameter("salary", salary);
			nq.setParameter("insured", insured);
			nq.executeUpdate()
			;
		}
		
	}

	@Override
	public void updateSalaryByEmpIdUsingQueryAnnotation(int empId, int salary) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIdUsingQueryAnnotation(int empId) {
		// TODO Auto-generated method stub
		
	}

}
