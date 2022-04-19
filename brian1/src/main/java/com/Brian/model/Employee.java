package com.Brian.model;

import java.sql.Date;

public class Employee extends User{
	public int accessLevel = 3;
	
	public Employee(int userId, String userName, String password, String firstName, String lastName, String street,
			String city, String state, int zip) {
		super(userId, userName, password, firstName, lastName, street, city, state, zip);
	}

	public Employee(User user) {
		super();
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.street = user.getStreet();
		this.city = user.getCity();
		this.state = user.getState();
		this.zip = user.getZip();
		this.accessLevel = user.getAccessLevel();
		
	}
	public Employee(int userId, String userName, String password, String firstName, String lastName, String street, String city,
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
	
}
