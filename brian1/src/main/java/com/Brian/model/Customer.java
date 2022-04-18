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
	// list of accounts managed/owned by customer
	protected ArrayList<Account> managedAccounts;

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
	
	public Customer(User user) {
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
		this.managedAccounts = this.findAllManaged(this);
	}


	
	
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
	public ArrayList<Account> findAllManaged(Customer customer){		
		return ManagerList.findAllManaged(customer);
	}
	
	public static Customer findByUserName(String username) {
		ArrayList<Customer> customers = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		final String SQL = "select * from users where user_name = ?";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			set = stmt.executeQuery();
			while(set.next()) {
				customers.add(new Customer(
						set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getString(5),
						set.getString(6),
						set.getString(7),
						set.getString(8),
						set.getInt(9),
						set.getInt(10)
					));
			}
			
			
		}catch(SQLException e){
			e.getStackTrace();
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		}
		if(customers.size() > 0) {
			return customers.get(0);
		}
		else
			return null;
	}

	
}
