package com.revature.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class Customer extends User{

	public Customer(int userId, String userName, String password, String firstName, String lastName, String address,
			Date birthday) {
		super(userId, userName, password, firstName, lastName, address, birthday);
		
	}

	// list of accounts managed/owned by customer
	protected ArrayList<Account> managedAccounts;

	

	
}
