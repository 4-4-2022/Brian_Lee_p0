package com.Brian.model;

import java.sql.Date;

public class Employee extends User{
	
	public Employee(int userId, String userName, String password, String firstName, String lastName, String street,
			String city, String state, int zip) {
		super(userId, userName, password, firstName, lastName, street, city, state, zip);
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
