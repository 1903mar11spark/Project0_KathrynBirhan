package com.revature.beans;

public class Accounts {
	public Accounts() {
		super();
		}
	
	public Accounts(int accountid, String AccountType, double balance, int userid) {
		super();
		this.accountid=accountid;
		this.userid=userid;
		this.balance=balance;
		this.AccountType=AccountType;
	}
	private int accountid;
	private int userid;
	private double balance;
	private String AccountType;
	public int getId() {
		return accountid;
	}

	public void setaccountId(int accountid) {
		this.accountid = accountid;
	}

	public int getuserid() {
		return userid;
	}

	public void setuserid(int userid) {
		this.userid = userid;
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
		return "Account [accountid=" + accountid + ", userid=" + userid + ", balance=" + balance + ", AccountType=" + AccountType
				+ "]";
	}
	
}
