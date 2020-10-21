package com.blazdemsar.integration.dto;

import java.util.HashSet;
import java.util.Set;

public class User {
    
    private Long userId;
    
    private String username;
    
    private String password;
    
    private String email;
    
    private String phoneNumber;
    
    private Set<Role> roles = new HashSet<>();
    
    public User () {
    	
    }
    
    public User(String username, String password, String email, String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/*
	 * @Override public String toString() { return "User [userId=" + userId +
	 * ", username=" + username + ", password=" + password + ", email=" + email +
	 * ", phoneNumber=" + phoneNumber + ", roles=" + roles + "]"; }
	 */
    
}
