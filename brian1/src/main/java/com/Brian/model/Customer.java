package com.Brian.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import com.Brian.util.ConnectionFactory;
import com.Brian.util.ResourceCloser;

public class Customer extends User{
	public ArrayList<Account> accounts;
	public int accessLevel = 1;

	public Customer(int userId, String userName, String password, String firstName, String lastName, String street, String city,
			String state, int zip) {
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
		
	}
	public Customer(int userId, String userName, String password, String firstName, String lastName, String street, String city,
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

	public Customer() {
		super();
	}

	// list of accounts managed/owned by customer
	protected ArrayList<Account> managedAccounts;
	
	
	public void save(Customer customer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		//insert into customers value();
		final String SQL = "insert into users values(default,?,?,?,?,?,?,?,?,1)";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, customer.getUserName());
			stmt.setString(2, customer.getPassword());
			stmt.setString(3, customer.getFirstName());
			stmt.setString(4, customer.getLastName());
			stmt.setString(5, customer.getStreet());
			stmt.setString(6, customer.getCity());
			stmt.setString(7, customer.getState());
			stmt.setInt(8, customer.getZip());
			stmt.executeQuery();
			
		}catch(SQLException e){
			return;
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		}
	}
	
	public Customer findOneCustomer() {
		return null;
	}
	
	public ArrayList<Customer> findAllCustomers() {
		return null;
		
	}
	
	public Customer updateCustomer() {
		return null;
	}
	
	public Customer deleteCustomer() {
		return null;
	}
	
	

	
	// Finding all accounts owned by single user
	public static ArrayList<Account> findAllOwned(Customer customer) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		final String SQL = "select * from accounts where account_owner = ?";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, customer.getUserId());
			set = stmt.executeQuery();
			while(set.next()) {
				accounts.add(new Account(
						set.getInt(1),						
						set.getFloat(2),
						set.getInt(3)
					));
			}
			
			
		}catch(SQLException e){
			e.getStackTrace();
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		}
		return accounts;
	}
	// method for retrieving all "managed" accounts
	

	
}
