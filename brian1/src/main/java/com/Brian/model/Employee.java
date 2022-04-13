package com.Brian.model;

import java.sql.Date;

public class Employee extends User{
	
	public Employee(int userId, String userName, String password, String firstName, String lastName, String address,
			Date birthday) {
		super(userId, userName, password, firstName, lastName, address, birthday);
	}
}
