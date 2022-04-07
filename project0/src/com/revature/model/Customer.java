package com.revature.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class Customer {
	// account number in SQL DB
	protected int userId;
	// list of accounts managed/owned by customer
	protected ArrayList<Account> managedAccounts;
	// username registered for customerNumber
	protected String userName;
	// password hashed. registered for customerNumber
	protected String password;
	// personal information for customer
	private String firstName;
	private String lastName;
	private String address;
	private Date birthday;
	

	
}
