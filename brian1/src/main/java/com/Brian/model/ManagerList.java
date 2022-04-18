package com.Brian.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import com.Brian.util.ConnectionFactory;
import com.Brian.util.ResourceCloser;



public class ManagerList {
	public int accountId;
	public ArrayList<Customer> managers;
	public ArrayList<Account> managedAccounts;
	
	
	public ManagerList(Account account) {
		super();
		this.accountId = account.getAccountId();
		this.managers = this.findAllManagers(account);
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public ArrayList<Customer> getManagers() {
		return managers;
	}


	public void setManagers(ArrayList<Customer> managers) {
		this.managers = managers;
	}
	
	


	@Override
	public String toString() {
		return "ManagerList [accountId=" + accountId + ", managers=" + managers + "]";
	}
	
	public static ArrayList<Customer> findAllManagers(Account account){
		ArrayList<Customer> managers = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		final String SQL = "select users.user_id, user_name , user_password , user_fname , "
				+ "user_lname , user_street, user_city, user_state , user_zip , user_accesslevel  "
				+ "from managers join users on managers.user_id = users.user_id  where account_id = ?";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, account.getAccountId());
			set = stmt.executeQuery();
			while(set.next()) {
				managers.add(new Customer(
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
			ResourceCloser.closeResultSet(set);
			ResourceCloser.closeStatement(stmt);
		}

		
		
		return managers;
	}
	
	public static void addManager(Account account, Customer customer){
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "insert into managers values(?,?)";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, customer.getUserId());
			stmt.setInt(2, account.getAccountId());
			stmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		}


	}
	public static void removeManager(Account account, Customer customer){
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "delete from managers where user_id = ? AND account_id = ?";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, customer.getUserId());
			stmt.setInt(2, account.getAccountId());
			stmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		}


	}
	
	public static ArrayList<Account> findAllManaged(Customer customer){
		ArrayList<Account> accounts = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		final String SQL = "select accounts.account_id, account_funds, account_owner from managers "
				+ "join accounts on managers.account_id = accounts.account_id where user_id = ?";
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
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeResultSet(set);
			ResourceCloser.closeStatement(stmt);
		}

		
		
		return accounts;
	}
	
	
}



