package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Ledger;
import com.revature.util.ConnectionUtil;
import com.revature.beans.Accounts;
import com.revature.beans.Transactions;

public class BankDaoImplementation implements BankDAO{

	@Override
	public List<Ledger> getLedger() {
		List<Ledger> l1 = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String sql = "SELECT USER_ID, USERNAME, PASS, FIRSTNAME, LASTNAME FROM LEDGER ";
			PreparedStatement stmt = con.prepareCall(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int userId = rs.getInt("USER_ID");
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASS");
				String finame = rs.getString("FIRSTNAME");
				String laname = rs.getString("LASTNAME");
				
				l1.add(new Ledger(userId, username, password,finame,laname));
			}
		}catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return l1;
	}
	
	/*
	@Override
	public Ledger getLedgerByID(int id)  {
		Ledger l = null;
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String sql = "SELECT USER_ID, PASS, FIRSTNAME, LASTNAME FROM LEDGER ";
			PreparedStatement stmt = con.prepareCall(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				int userid = rs.getInt("USER_ID");
				String password = rs.getString("PASS");
				String fname = rs.getString("FIRSTNAME");
				String lname = rs.getString("LASTNAME");
				
				l= new Ledger(userid, password,fname,lname);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
		return l;
	}
*/
	@Override
	public void CreateNewUser(Ledger ledger) {
		
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "INSERT INTO LEDGER ("
							+" USER_ID,"
							+" USERNAME,"
							+" PASS,"
							+" FIRSTNAME,"
							+" LASTNAME ) VALUES ("
							+"NULL,?, ?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, ledger.getUsername());
			stmt.setString(2, ledger.getPass());
			stmt.setString(3, ledger.getFirstname());
			stmt.setString(4, ledger.getLastname());
			
			stmt.executeUpdate();
			stmt.close();

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void updateLedger(Ledger ledger) {
		
		
	}

	@Override
	public void deleteLedger(Ledger ledger) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Ledger getLedgerByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void MakeDeposit(Accounts account) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "UPDATE ACCOUNT SET BALANCE = BALANCE + ? " +"WHERE ID="
							+" USER_ID,"
							+" PASS,"
							+" FIRSTNAME,"
							+" LASTNAME ) VALUES ("
							+"NULL, ?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setDouble(1, account.getBalance());
			
			
			stmt.executeUpdate();
			stmt.close();

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void MakeWithdrawal(Accounts account) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "UPDATE ACCOUNT SET BALANCE = BALANCE - ? " +"WHERE ID="
							+" USER_ID,"
							+" PASS,"
							+" FIRSTNAME,"
							+" LASTNAME ) VALUES ("
							+"NULL, ?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setDouble(1, account.getBalance());
			
			
			stmt.executeUpdate();
			stmt.close();

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void CreateNewAccount(Accounts account) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "INSERT INTO ACCOUNT ("
							+" ACCOUNT_ID,"
							+" ACCOUNT_TYPE,"
							+" ACCOUNT_BALANCE,"
							+" USER_ID ) VALUES ("
							+"NULL, ?,?,NULL)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, account.getAccountType());
			stmt.setDouble(2, account.getBalance());
			
			
			stmt.executeUpdate();
			stmt.close();

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Accounts> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}



	

	
	
	
	
	
	
	
}
