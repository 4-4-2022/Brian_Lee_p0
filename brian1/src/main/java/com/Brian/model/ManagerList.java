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
		final String SQL = "select * from managers where account_id = ?";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, account.getAccountId());
			set = stmt.executeQuery(SQL);
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
	
	public void addManager(Customer customer){
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "insert into managers values(?,?)";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, customer.getUserId());
			stmt.setInt(2, this.getAccountId());
			stmt.executeQuery(SQL);

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		}


	}
	public void removeManager(Customer customer){
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "delete from managers where user_id = ? AND account_id = ?";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, customer.getUserId());
			stmt.setInt(2, this.getAccountId());
			stmt.executeQuery(SQL);

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		}


	}
	
}



