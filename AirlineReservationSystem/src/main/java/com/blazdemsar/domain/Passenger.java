package com.blazdemsar.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Passenger {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long passengerId;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private String email;
	
	private String phoneNumber;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	private IdentificationType passport;
	
	public Passenger () {
		super();
	}

	public Passenger(Long passengerId, String firstName, String middleName, String lastName, Gender gender,
			String email, String phoneNumber, LocalDate dob, IdentificationType passport) {
		super();
		this.passengerId = passengerId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.passport = passport;
	}
	
	public Passenger(String firstName, String middleName, String lastName, Gender gender,
			String email, String phoneNumber, LocalDate dob, IdentificationType passport) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.passport = passport;
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public IdentificationType getPassport() {
		return passport;
	}

	public void setPassport(IdentificationType passport) {
		this.passport = passport;
	}
	
}
