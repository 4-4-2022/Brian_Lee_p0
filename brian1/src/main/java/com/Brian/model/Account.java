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
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getFunds() {
		return funds;
	}
	public void setFunds(int funds) {
		this.funds = funds;
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
		return "Account [accountId=" + accountId + ", funds=" + funds + ", ownerId=" + ownerId + ", managers="
				+ managers + "]";
	}

	
	
}
