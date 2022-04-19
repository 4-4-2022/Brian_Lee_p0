package com.Brian.model;

import java.sql.Date;

public class Admin extends Employee{

	public int accessLevel = 4;
	
	public Admin(int userId, String userName, String password, String firstName, String lastName, String street, String city,
			String state, int zip) {
		super(userId, userName, password, firstName, lastName, street, city, state, zip);
		// TODO Auto-generated constructor stub
	}

	public Admin(int userId, String userName, String password, String firstName, String lastName, String street, String city,
			String state, int zip, int accessLevel) {
		super(userId, userName, password, firstName, lastName, street, city, state, zip);
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.accessLevel = accessLevel;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	@Override
	public String toString() {
		return "Admin [accessLevel=" + accessLevel + "]";
	}
	
	// method 

}
