package com.Brian.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import com.Brian.util.ConnectionFactory;
import com.Brian.util.ResourceCloser;

public class Account {
	protected int accountId;
	protected float funds;
	protected int ownerId;
	
	public Account(int funds, int ownerId) {
		super();
		this.funds = funds;
		this.ownerId = ownerId;
	}
	
	public Account() {
		super();
	}

	public Account(int accountId, float funds, int ownerId) {
		this.accountId = accountId;
		this.funds = funds;
		this.ownerId = ownerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, funds, managers, ownerId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountId == other.accountId && funds == other.funds && Objects.equals(managers, other.managers)
				&& ownerId == other.ownerId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public float getFunds() {
		return funds;
	}
	public void setFunds(float funds) {
		this.funds = funds;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", funds=" + funds + ", ownerId=" + ownerId+ "]";
	}
	// finding one account
	public static Account findOne(int accountId) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		final String SQL = "select * from accounts where account_id = ?";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, accountId);
			set = stmt.executeQuery();
			
			while(set.next()) {
				accounts.add(new Account(
						set.getInt(1),
						set.getFloat(2),
						set.getInt(3)

						));
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
					ResourceCloser.closeConnection(conn);
					ResourceCloser.closeStatement(stmt);
			}
		
		if (accounts.size() > 0) {
			return accounts.get(0);
		}
		else {
			System.out.println("no bueno, account not found!");
			return accounts.get(0);
		}
	}
	
	// creating and saving accounts
	
	public void save() {
		Connection conn = null;
		PreparedStatement stmt = null;
		//insert into customers value();
		final String SQL = "insert into accounts values(default,?,?)";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setFloat(1, this.funds);
			stmt.setInt(2, this.ownerId);
			stmt.executeQuery();
			
		}catch(SQLException e){
			return;
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		}
	}
	// closing account method for owner and admins
	
	// method for decreasing amount of funds
	public float reduceFunds(float amount) {
		// access database, get funds, reduce funds by amount
		
		return funds;
	}
	
	// method for increasing amount of funds
	
	// method for transferring funds between accounts
	
	// method to add managers
	
	// method to remove managers
	
	

	
	
}
