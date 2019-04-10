package com.revature.beans;

import java.sql.Timestamp;

public class Transactions {
	public Transactions() {
		super();
	}
	public Transactions(int id, Accounts account, double amount, Timestamp timestamp) {
		this.id=id;
		this.account=account;
		this.amount=amount;
		this.timestamp=timestamp;
	}
	
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", account=" + account + ", amount=" + amount + ", timestamp=" + timestamp
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Accounts getAccount() {
		return account;
	}
	public void setAccount(Accounts account) {
		this.account = account;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	private int id;
	private Accounts account;
	private double amount;
	private Timestamp timestamp;
}
