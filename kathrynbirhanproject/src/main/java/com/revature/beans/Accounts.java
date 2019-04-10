package com.revature.beans;

public class Accounts {
	public Accounts() {
		super();
		}
	
	public Accounts(int id, Ledger ledger, double balance, String AccountType) {
		super();
		this.id=id;
		this.ledger=ledger;
		this.balance=balance;
		this.AccountType=AccountType;
	}
	private int id;
	private Ledger ledger;
	private double balance;
	private String AccountType;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ledger getLedger() {
		return ledger;
	}

	public void setLedger(Ledger ledger) {
		this.ledger = ledger;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", ledger=" + ledger + ", balance=" + balance + ", AccountType=" + AccountType
				+ "]";
	}
	
}
