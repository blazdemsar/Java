package com.synergisticit.domain;
/*
use blazdb;
create table user(
		userid int primary key not null auto_increment,
		username varchar(255),
	password varchar(255),
	email varchar(255),
	valid boolean
		);*/
public class User {
	private int userid;
	private String username;
	private String password;
	private String email;
	private boolean valid;
	
	public User() {}

	public User(int userid, String username, String password, String email, boolean valid) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.valid = valid;
	}

	public User(String username, String password, String email, boolean valid) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.valid = valid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", valid=" + valid + "]";
	}
	
	

}
