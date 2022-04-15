package com.Brian.model;

import java.util.ArrayList;
import java.util.Objects;

public class Account {
	protected int accountId;
	protected int funds;
	protected int ownerId;
	protected ArrayList<Customer> managers;
	
	public Account(int funds, int ownerId) {
		super();
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
	private int getAccountId() {
		return accountId;
	}
	private void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	private int getFunds() {
		return funds;
	}
	private void setFunds(int funds) {
		this.funds = funds;
	}
	private int getOwnerId() {
		return ownerId;
	}
	private void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	private ArrayList<Customer> getManagers() {
		return managers;
	}
	private void setManagers(ArrayList<Customer> managers) {
		this.managers = managers;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", funds=" + funds + ", ownerId=" + ownerId + ", managers="
				+ managers + "]";
	}
	// closing account method for owner and admins
	
	// method for decreasing amount of funds
	public int reduceFunds(int amount) {
		// access database, get funds, reduce funds by amount
		
		return funds;
	}
	
	// method for increasing amount of funds
	
	// method for transferring funds between accounts
	
	// method to add managers
	
	// method to remove managers
	
	

	
	
}
