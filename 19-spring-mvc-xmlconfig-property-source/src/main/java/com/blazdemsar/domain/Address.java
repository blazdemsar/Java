package com.blazdemsar.domain;

public class Address {
	
	private String addresssLine1;
	private String addresssLine2;
	private String city;
	private String state;
	
	public Address() {
		
	}

	public Address(String addresssLine1, String addresssLine2, String city, String state) {
		super();
		this.addresssLine1 = addresssLine1;
		this.addresssLine2 = addresssLine2;
		this.city = city;
		this.state = state;
	}

	public String getAddresssLine1() {
		return addresssLine1;
	}

	public void setAddresssLine1(String addresssLine1) {
		this.addresssLine1 = addresssLine1;
	}

	public String getAddresssLine2() {
		return addresssLine2;
	}

	public void setAddresssLine2(String addresssLine2) {
		this.addresssLine2 = addresssLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Address [addresssLine1=" + addresssLine1 + ", addresssLine2=" + addresssLine2 + ", city=" + city
				+ ", state=" + state + "]";
	}
	
	
}
