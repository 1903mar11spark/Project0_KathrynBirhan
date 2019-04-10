package com.revature.main;

import java.util.List;

import com.revature.beans.Ledger;
import com.revature.dao.BankDAO;
import com.revature.dao.BankDaoImplementation;
import java.util.Scanner;
public class Driver {


public static void main(String[] args) {
	System.out.println("Test1");
	System.out.println("Test2");
	BankDAO bd =  new BankDaoImplementation();
	
	
	Scanner myObj = new Scanner(System.in);
	System.out.println("Enter your Desired Username");
	String uname=myObj.nextLine();
	System.out.println("Enter your Desired password");
	String pass=myObj.nextLine();
	System.out.println("Enter your First Name");
	String fname=myObj.nextLine();
	System.out.println("Enter your Last Name");
	String lname=myObj.nextLine();
	myObj.close();
	Ledger led = new Ledger(uname,pass,fname,lname);
	bd.CreateNewUser(led);
	
	
	System.out.println("bd.getLedger is the problem");
	List<Ledger> ClientList = bd.getLedger();
	
	System.out.println("JK its the foreahloop");

	
	System.out.println("Test3");
	System.out.println(ClientList);
	System.out.println("Test4");
	System.out.println("Test5");
	for(Ledger l : ClientList) {
		System.out.println(l);
	}
	
	
	
}





}
	
	
	
	
	

	
	
	
	
	
	
	

