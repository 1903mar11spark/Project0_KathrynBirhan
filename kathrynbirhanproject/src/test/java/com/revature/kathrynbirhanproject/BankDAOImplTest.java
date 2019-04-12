package com.revature.kathrynbirhanproject;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.beans.Ledger;
import com.revature.dao.BankDaoImplementation;

public class BankDAOImplTest {
	

	BankDaoImplementation bdi = new BankDaoImplementation(); 
	
	@Test
	public void BadLoginTest()  {
		//wrong username & password entered
		boolean result = bdi.Login("kathryn" , "12345");
		assertTrue(result==false);
	}
	@Test
	public void ValidLoginTest()  {
		//valid username & password entered
		boolean result = bdi.Login("sauce" , "sauce");
		assertTrue(result == true);
	}
	@Test
	public void EmptyLoginTest()  {
		//no username & password entered
		boolean result = bdi.Login("" , "");
		assertTrue(result==false);
	}
	
	//NEED THE CODE TO TEST
	@Test
	public void updateLedgerTest() {
		fail();
	}
	//Test retrieving account with valid login
	@Test
	public void retrieveUserAccountsTest() {
		int result = bdi.retrieveUserAccounts("sauce", "sauce");
		assertEquals(2043, result);
	}
	//Test retrieving account with bad login
	@Test
	public void retrieveUserAccountsBadLoginTest() {
		int result = bdi.retrieveUserAccounts("sauce", "wrong");
		assertTrue(result!=2042);
	} 
}