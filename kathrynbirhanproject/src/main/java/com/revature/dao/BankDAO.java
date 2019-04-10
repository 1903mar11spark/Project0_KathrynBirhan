package com.revature.dao;

import java.util.List;

import com.revature.beans.Accounts;
import com.revature.beans.Ledger;

public interface BankDAO {
	public List<Ledger> getLedger();
	public List<Accounts> getAccounts();
	public Ledger getLedgerByID(int id);
	public void CreateNewUser(Ledger ledger);
	public void updateLedger(Ledger ledger);
	public void deleteLedger(Ledger ledger);
	public void CreateNewAccount(Accounts account);
	public void MakeDeposit(Accounts account);
	public void MakeWithdrawal(Accounts account);

}
