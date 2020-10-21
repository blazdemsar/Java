package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer empId;
	
	@NotEmpty
	//@Column(name="emp_name")
	@Column(name="name")
	private String name;
	
	@NotEmpty(message="must not be empty ***Javax Validation")
	private String job;
	
	@Max(value = 8500)
	@Min (value = 3500)
	private int salary;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Past
	private Date dob;
	
	private String gender;
	
	@ElementCollection(fetch=FetchType.LAZY) /* since sopkenLanguages is fetched after skills has been fetched, 
	there for it will fetch the associated spoken languages for each skills in cas eof EAGER fetching. 
	Therefore better to use LAZY fetching.*/
	
	private List<String>  spokenLanguages = new  ArrayList<>();
	
	@ElementCollection(fetch=FetchType.LAZY) // EAGER fetch will also do here becuase for a perticular employee it is fetching all the skills in one go.
	private Set<String> skills = new HashSet<>();
	
	
	
	private String[] hobbies;
	
	@Enumerated(EnumType.STRING)
	private EmployeeType employeeType;
	
	private boolean insured;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String mobile;
	

	private String nationality;
	
	/*@Valid
	 @Embedded
	private Address address;	
	*/
	
	@Valid
	@Embedded
	@AttributeOverrides(value = { 
			@AttributeOverride(name = "addressLine1", column=@Column(name="billing_addressLine1")), 
			@AttributeOverride(name = "addressLine2", column=@Column(name="billing_addressLine2")), 
			@AttributeOverride(name = "city", column=@Column(name="billing_city")), 
			@AttributeOverride(name = "state", column=@Column(name="billing_state")), 			
	})
	private Address billing_Address;
	
	@Valid
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="addressLine1", column=@Column(name="shipping_addressLine1")),
		@AttributeOverride(name="addressLine2", column=@Column(name="shipping_addressLine2")),
		@AttributeOverride(name="city", column=@Column(name="shipping_city")),
		@AttributeOverride(name="state", column=@Column(name="shipping_state")),
		
	})
	private Address shipping_Address;
	
	@Valid
	@OneToOne
	private Passport passport;
	
	@Valid
	@ManyToOne
	@JoinColumn(name="projectId")
	private Project project;
	
	public Employee() {
		
	}

	public Employee(Integer empId, String name, String job, int salary) {
		super();
		this.empId = empId;
		this.name = name;
		this.job = job;
		this.salary = salary;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
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

	public List<String> getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(List<String> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	public Set<String> getSkills() {
		return skills;
	}

	public void setSkills(Set<String> skills) {
		this.skills = skills;
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

	public Address getBilling_Address() {
		return billing_Address;
	}

	public void setBilling_Address(Address billing_Address) {
		this.billing_Address = billing_Address;
	}

	public Address getShipping_Address() {
		return shipping_Address;
	}

	public void setShipping_Address(Address shipping_Address) {
		this.shipping_Address = shipping_Address;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", job=" + job + ", salary=" + salary
				+ ", dob=" + dob + ", gender=" + gender + ", spokenLanguages=" + spokenLanguages + ", skills=" + skills
				+ ", hobbies=" + Arrays.toString(hobbies) + ", employeeType=" + employeeType + ", insured=" + insured
				+ ", email=" + email + ", mobile=" + mobile + ", nationality=" + nationality + ", billing_Address="
				+ billing_Address + ", shipping_Address=" + shipping_Address + ", passport=" + passport + ", project="
				+ project + "]";
	}

	
	

}
