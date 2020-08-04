package com.blazdemsar.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Employee implements InitializingBean, DisposableBean {
	
	private int empId;
	private String name;
	private String job;
	private int salary;
	
	private List<String> hobbies;
	private Map<String, String> instructor;
	private Set<String> skills;
	
	private Address address;
	
	public Employee() {
		
	}

	public Employee(int empId, String name, String job, int salary) {
		super();
		this.empId = empId;
		this.name = name;
		this.job = job;
		this.salary = salary;
	}

	public Employee(int empId, String name, String job, int salary, Address address) {
		super();
		this.empId = empId;
		this.name = name;
		this.job = job;
		this.salary = salary;
		this.address = address;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	
	public Map<String, String> getInstructor() {
		return instructor;
	}

	public void setInstructor(Map<String, String> instructor) {
		this.instructor = instructor;
	}

	public Set<String> getSkills() {
		return skills;
	}

	public void setSkills(Set<String> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", job=" + job + ", salary=" + salary + ", hobbies="
				+ hobbies + ", instructor=" + instructor + ", skills=" + skills + ", address=" + address + "]";
	}

	public void initMethod() {
		System.out.println("inside Employee.initMethod()....");
	}
	
	public void destroyMethod() {
		System.out.println("inside Employee.destroyMethod()....");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet() was called....");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("destroy() was called....");
	}
}
