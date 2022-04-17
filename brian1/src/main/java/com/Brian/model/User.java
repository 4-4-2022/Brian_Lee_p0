package com.Brian.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.Brian.util.ConnectionFactory;
import com.Brian.util.ResourceCloser;

public class User {
	// account number in SQL DB
	protected int userId;
	// username registered for customerNumber
	protected String userName;
	// password hashed. registered for customerNumber
	protected String password;
	// personal information for customer
	protected String firstName;
	protected String lastName;
	protected String street;
	protected String city;
	protected String state;
	protected int zip;
	public int accessLevel = 0;
	
	public User() {
		super();
	}
	
	public User(int userId, String userName, String password, String firstName, String lastName, String street,
			String city, String state, int zip) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.state = state;
		this.zip = zip;
	}

	public User(int userId, String userName, String password, String firstName, String lastName, String street, String city, String state,
			int zip, int accesslevel) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.accessLevel = accesslevel;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String address) {
		this.street = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	
	
	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zip=" + zip + ", accessLevel=" + accessLevel + "]";
	}

	public static User findOne(String username, String password) {
		ArrayList<User> users = new ArrayList<User>();
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
				users.add(new User(
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
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
					ResourceCloser.closeConnection(conn);
					ResourceCloser.closeStatement(stmt);
			}
		
		if (users.size() > 0) {
			return users.get(0);
		}
		else {
			System.out.println("no bueno, user not found");
			return users.get(0);
		}
	}

	
}
