package com.Brian.model;

import java.sql.Date;

public abstract class User {
	// account number in SQL DB
	protected int userId;
	// username registered for customerNumber
	protected String userName;
	// password hashed. registered for customerNumber
	protected String password;
	// personal information for customer
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private int zip;
	
	public User() {
		super();
	}
	
	public User(int userId, String userName, String password, String firstName, String lastName, String street,
			String city, String state, int zip) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String address) {
		this.street = address;
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

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	
	
}
