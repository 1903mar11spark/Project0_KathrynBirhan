package com.revature.beans;

public class Ledger {
	
	public Ledger() {
		super();
	}
	public Ledger (String username, String pass, String firstname, String lastname) {
		super();
		this.pass=pass;
		this.firstname = firstname;
		this.lastname=lastname;
		this.username=username;
	}
	public Ledger (int id, String username, String pass, String firstname, String lastname) {
		super();
		this.pass=pass;
		this.id = id;
		this.firstname = firstname;
		this.lastname=lastname;
		this.username=username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	private int id;
	private String username;

	private String pass;
	private String firstname;
	private String lastname;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Override
	public String toString() {
		return "Ledger [id=" + id+ ", username=" + username + ", pass=" + pass + ", firstname=" + firstname + ", lastname=" + lastname
				 + "]";
	}




	
		   
}
