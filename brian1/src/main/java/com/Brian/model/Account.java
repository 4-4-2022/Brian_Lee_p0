package com.Brian.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Brian.respository.HotdogRespositoryImp;
import com.Brian.util.ConnectionFactory;
import com.Brian.util.ResourceCloser;

public class Account {
	protected int accountId;
	protected float funds;
	protected int ownerId;
	public ArrayList<Customer> managers;
	protected String ownerUserName;
	protected String ownerFirstName;
	protected String ownerLastName;
	
	private static final Logger logger = LoggerFactory.getLogger(Account.class);
	
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


	public Account(int accountId, float funds, int ownerId, String ownerUserName, String ownerFirstName, String ownerLastName) {
		super();
		this.accountId = accountId;
		this.funds = funds;
		this.ownerId = ownerId;

		this.ownerUserName = ownerUserName;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
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
	
	

	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
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
	
	public String toStringEmp() {
		return "Account [accountId=" + accountId + ", funds=" + funds + ", owner: username=" + ownerUserName+ ", first name=" 
	+ ownerFirstName+ ", last name=" + ownerLastName+ "]";
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
			logger.info("The retrieved account is:" + accounts.get(0));
			return accounts.get(0);
		}
		else {
			System.out.println("no bueno, account not found!");
			return accounts.get(0);
		}
	}
	
	//Finding all accounts
	public static ArrayList<Account> findAll() {
		ArrayList<Account> accounts = new ArrayList<Account>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		final String SQL = "select account_id, account_funds, account_owner, user_name, user_fname, user_lname from accounts join "
				+ "users on account_owner = users.user_id";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			set = stmt.executeQuery();
			
			while(set.next()) {
				accounts.add(new Account(
						set.getInt(1),
						set.getFloat(2),
						set.getInt(3),
						set.getString(4),
						set.getString(5),
						set.getString(6)
						));
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
					ResourceCloser.closeConnection(conn);
					ResourceCloser.closeStatement(stmt);
			}
		
		if (accounts.size() < 1) {
			System.out.println("No accounts found");
			return accounts;
		}
		else { 
			logger.info("The retrieved accounts are:" + accounts);
			return accounts;
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
		ManagerList.removeManagerWithAccount(account);
		PreparedStatement stmt = null;
		final String SQL = "delete from accounts where account_id = ?";
		
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, account.getAccountId());
			stmt.executeUpdate();
			
		}catch(SQLException e){
			return;
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		}
		logger.info("The closed account is:" + account);
	}
	
	// method for decreasing amount of funds
	public Account reduceFunds(Account account, float amount) {
		// access database, get funds, reduce funds by amount
		if (amount < 0) {
			System.out.println("Can not reduce by negative amount");
			return account;
		}
		if ( account.getFunds() - amount >= 0 && amount >= 0) {
			float newAmt = this.getFunds() - amount;
			Connection conn = null;
			PreparedStatement stmt = null;
			final String SQL = "update accounts set account_funds = ? where account_id = ?";
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.setFloat(1, newAmt);
				stmt.setInt(2, account.accountId);
				
				stmt.executeUpdate();
				account.setFunds(newAmt);
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				ResourceCloser.closeConnection(conn);
				ResourceCloser.closeStatement(stmt);
			}
			return account;
		}
		else if(account.funds - amount < 0){
			System.out.println("Account Id: "+account.getAccountId()+" has Insufficient funds");
			return account;
		}
		logger.info(account+ " reduced by " + amount);
		return account;
	}
	
	// method for increasing amount of funds
	public Account addFunds(Account account, float amount) {
		// access database, get funds, reduce funds by amount
		if (amount < 0) {
			System.out.println("Can not add by negative amount");
			return account;
		}
		else {
			float newAmt = account.getFunds() + amount;
			Connection conn = null;
			PreparedStatement stmt = null;
			final String SQL = "update accounts set account_funds = ? where account_id = ?";
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.setFloat(1, newAmt);
				stmt.setInt(2, account.accountId);
				
				stmt.executeUpdate();
				account.setFunds(newAmt);
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				ResourceCloser.closeConnection(conn);
				ResourceCloser.closeStatement(stmt);
			}
			logger.info(account+ " increased by " + amount);
			return account;
		}

		
	}
	// method for transferring funds between accounts
	public static void transferFunds(Account account, Account otherAccount, float amount) {
		if(amount == 0 ) {
			System.out.println("Nothing done, 0 amount was specified.");
		}
		else if(amount > 0) {
			if(amount <= otherAccount.getFunds()) {
				otherAccount.reduceFunds(otherAccount, amount);
				account.addFunds(account, amount);
			}
			else {
				System.out.println("Insufficient Funds!");
			}
		}
		else if (amount < 0) {
			if(amount <= account.getFunds()) {
				float absAmount = Math.abs(amount);
				account.reduceFunds(account, absAmount);
				otherAccount.addFunds(otherAccount, absAmount);
			}
			else {
				System.out.println("Insufficient Funds!");
			}

		}
		else {
			System.out.println("Could not transfer");
		}
		logger.info(account+ " transferred " + amount + " to " + otherAccount);

	}
	// method to add managers
	public void addManager(Customer customer) {
		ManagerList.addManager(this, customer);
	}
	// method to remove managers
	public void removeManager(Customer customer) {
		ManagerList.removeManager(this, customer);
	}
	
	public void addFundsInst(float amount) {
		if (amount < 0) {
			System.out.println("Can not add by negative amount");
		}else {
			float newAmt = this.getFunds() + amount;
			this.setFunds(newAmt);
		}
		
	}
	
	public void reduceFundsInst(float amount) {
		if (amount < 0) {
			System.out.println("Can not reduce by negative amount");
		}
		if ( this.getFunds() - amount >= 0 && amount >= 0) {
			float newAmt = this.getFunds() - amount;
			this.setFunds(newAmt);
		}
		else if(this.funds - amount < 0){
			System.out.println("Account Id: "+this.getAccountId()+" has Insufficient funds");
		}
	}
	
	public static void transferFundsInst(Account account, Account otherAccount, float amount) {
		if(amount == 0 ) {
			System.out.println("Nothing done, 0 amount was specified.");
		}
		else if(amount > 0) {
			if(amount <= otherAccount.getFunds()) {
				otherAccount.reduceFundsInst(amount);
				account.addFundsInst(amount);
			}
			else {
				System.out.println("Insufficient Funds!");
			}

		}
		else if (amount < 0) {
			if(amount <= account.getFunds()) {
				float absAmount = Math.abs(amount);
				account.reduceFundsInst(absAmount);
				otherAccount.addFundsInst(absAmount);
			}
			else {
				System.out.println("Insufficient Funds!");
			}

		}
		else {
			System.out.println("Could not transfer");
		}
		logger.info(account+ " transferred " + amount + " to " + otherAccount);

	}

}
	

	
	

