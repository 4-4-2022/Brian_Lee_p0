package com.Brian.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
		final String SQL = "insert into customers values(?,?,?,?,?,?,?,?,?)";
		
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
			stmt.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
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
	
	// method for retrieving all owned accounts
	
	// method for retrieving all "managed" accounts
	

	
}
