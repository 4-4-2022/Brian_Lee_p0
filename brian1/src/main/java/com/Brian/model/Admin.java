package com.Brian.model;

import java.sql.Date;

public class Admin extends Employee{

	public int accessLevel = 4;
	
	public Admin(int userId, String userName, String password, String firstName, String lastName, String street, String city,
			String state, int zip) {
		super(userId, userName, password, firstName, lastName, street, city, state, zip);
		// TODO Auto-generated constructor stub
	}

	public Admin() {
		// TODO Auto-generated constructor stub
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
