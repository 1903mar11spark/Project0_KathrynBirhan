package com.revature.dao;

import java.util.List;

import com.revature.beans.Accounts;
import com.revature.beans.Ledger;

public interface BankDAO {
	public List<Ledger> getLedger();
	public List<Accounts> getAccounts(int num);
	public Ledger getLedgerByID(int id);
	public void CreateNewUser(Ledger ledger);
	public void deleteAccounts(int userid);
	public void CreateNewCheckingAccount(int i);
	public void CreateNewSavingsAccount(int i);
	public void MakeDeposit(int accountNum, double deposit);
	public void MakeWithdrawal(int accountNum, double withdrawal);
	public boolean Login(String userlogin, String passlogin);
	public Accounts accountAfterTransaction(int accountNum);
	public boolean ItsNotYourAccount(int accountNum, int userid);
	public int retrieveUserAccounts(String userlogin, String passlogin);
	public void DeleteYoAccount(int Account_id);
	public void SuperDeleteAMember(int user_id);
	public void SuperDeleteAllAccounts();
	public int retrieveAccountBalance(int Account_id);
	public void SuperUpdateFirstName(String firstname, int userid);
	public void SuperUpdateLastName(String lastname, int userid);
	public void SuperUpdateUserName(String Username,int userid);
	public void SuperUpdatePassword(String pass, int userid);
	public Boolean MemberExists(int userid);

}
