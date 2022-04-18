package com.Brian.model;

import java.math.BigDecimal;
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
	public ArrayList<Customer> managers;
	
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
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + Float.floatToRawIntBits(funds);
		result = prime * result + ownerId;
		return result;
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
		if (accountId != other.accountId)
			return false;
		if (Float.floatToRawIntBits(funds) != Float.floatToRawIntBits(other.funds))
			return false;
		if (ownerId != other.ownerId)
			return false;
		return true;
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
	public void setFunds(float f) {
		this.funds = f;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	

	public ArrayList<Customer> getManagers() {
		return managers;
	}

	public void setManagers(ArrayList<Customer> managers) {
		this.managers = managers;
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
	public static void closeAccount(Account account) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "delete from accounts where account_id = ?";
		
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, account.accountId);
			stmt.executeQuery();
			
		}catch(SQLException e){
			return;
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		}
	}
	
	// method for decreasing amount of funds
	public float reduceFunds(Account account, float amount) {
		// access database, get funds, reduce funds by amount
		if (amount < 0) {
			System.out.println("Can not reduce by negative amount");
			return account.funds;
		}
		if ( account.funds - amount >= 0 && amount >= 0) {
			float newAmt = this.funds - amount;
			Connection conn = null;
			PreparedStatement stmt = null;
			final String SQL = "update accounts set account_funds = ? where account_id = ?";
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.setFloat(1, newAmt);
				stmt.setInt(2, account.accountId);
				
				stmt.executeUpdate();
				account.funds = newAmt;
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				ResourceCloser.closeConnection(conn);
				ResourceCloser.closeStatement(stmt);
			}
			
			
			
			
			return this.funds - amount;
		}
		else if(account.funds - amount < 0){
			System.out.println("Account Id: "+account.getAccountId()+" has Insufficient funds");
			return account.funds;
		}
		
		return account.funds;
	}
	
	// method for increasing amount of funds
	public float addFunds(Account account, float amount) {
		// access database, get funds, reduce funds by amount
		if (amount < 0) {
			System.out.println("Can not add by negative amount");
			return account.funds;
		}
		else if ( account.funds - amount >= 0 && amount >= 0) {
			float newAmt = this.funds + amount;
			Connection conn = null;
			PreparedStatement stmt = null;
			final String SQL = "update accounts set account_funds = ? where account_id = ?";
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.setFloat(1, newAmt);
				stmt.setInt(2, account.accountId);
				
				stmt.executeUpdate();
				account.funds = newAmt;
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				ResourceCloser.closeConnection(conn);
				ResourceCloser.closeStatement(stmt);
			}
			return this.funds - amount;
		}

		
		return account.funds;
	}
	// method for transferring funds between accounts
	public static void transferFunds(Account account, Account otherAccount, float amount) {
		if(amount == 0 ) {
			System.out.println("Nothing done, 0 amount was specified.");
		}
		else if(amount > 0) {
			otherAccount.reduceFunds(otherAccount, amount);
			account.addFunds(account, amount);
			
		}
		else if (amount < 0) {
			float absAmount = Math.abs(amount);
			account.reduceFunds(account, absAmount);
			otherAccount.addFunds(otherAccount, absAmount);
		}
		else {
			System.out.println("Could not transfer");
		}
		
	}
	// method to add managers
	public void addManager(Customer customer) {
		ManagerList.addManager(this, customer);
	}
	// method to remove managers
	public void removeManager(Customer customer) {
		ManagerList.removeManager(this, customer);
	}
	

	
	
}
