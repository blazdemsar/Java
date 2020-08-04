package com.blazdemsar.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Min;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int empId;
	
	//@Column(name="NAME")
	//@NotEmpty
	private String name;
	
	//@NotEmpty
	private String job;
	
	//@Min(value=3000)
	private int salary;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dob;
	
	private String gender;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private Set<String> skills = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String>  spokenLanguages = new  ArrayList<>();
	
	private String[] hobbies;
	
	private EmployeeType employeeType;
	
	private boolean insured;
	
	//@NotEmpty
	private String email;
	
	//@NotEmpty
	private String mobile;
	
	//@NotEmpty
	private String nationality;
	
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<String> getSkills() {
		return skills;
	}

	public void setSkills(Set<String> skills) {
		this.skills = skills;
	}

	public List<String> getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(List<String> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	public String[] getHobbies() {
		return hobbies;
	}

	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public boolean isInsured() {
		return insured;
	}

	public void setInsured(boolean insured) {
		this.insured = insured;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", job=" + job + ", salary=" + salary
				+ ", dob=" + dob + ", gender=" + gender + ", skills=" + skills + ", spokenLanguages=" + spokenLanguages
				+ ", hobbies=" + Arrays.toString(hobbies) + ", employeeType=" + employeeType + ", insured=" + insured
				+ ", email=" + email + ", mobile=" + mobile + ", nationality=" + nationality + ", address=" + address
				+ "]";
	}
	
	
	
	
	
	

}
