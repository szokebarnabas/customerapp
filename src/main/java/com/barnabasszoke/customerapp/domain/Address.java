package com.barnabasszoke.customerapp.domain;

public class Address {

	private String city;
	private String street;
	private String state;
	private String postalCode;

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String city, String street, String state, String postalCode) {
		super();
		this.city = city;
		this.street = street;
		this.state = state;
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
