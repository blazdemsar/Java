package com.blazdemsar.beans;

public class Employee {
	
	private int empId = 1;
	private String name = "Blaz";
	private String job = "Programmer";
	private int salary = 2000;
	
	public Employee() {
		
	}

	public Employee(int empId, String name, String job, int salary) {
		super();
		this.empId = empId;
		this.name = name;
		this.job = job;
		this.salary = salary;
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
	
	
	
}
