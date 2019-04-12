package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
							+"Null,?, ?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, ledger.getUsername());
			stmt.setString(2, ledger.getPass());
			stmt.setString(3, ledger.getFirstname());
			stmt.setString(4, ledger.getLastname());
			
			stmt.executeUpdate();
			stmt.close();

			}catch (SQLIntegrityConstraintViolationException reason ) { 
				System.out.println("[ERROR] - Username already taken!");
			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void CreateNewSavingsAccount(int i) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "INSERT INTO ACCOUNTS (ACCOUNT_ID, ACCOUNT_TYPE, BALANCE, USER_ID) VALUES (NULL, 'SAVINGS',0.00,?)";
					
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, i);
			

			
			stmt.executeUpdate();
			stmt.close();

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void CreateNewCheckingAccount(int i) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "INSERT INTO ACCOUNTS (ACCOUNT_ID, ACCOUNT_TYPE, BALANCE, USER_ID) VALUES (1, 'CHECKING',0.00,?)";

			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, i);
			
			
			
			stmt.executeUpdate();
			stmt.close();

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void MakeDeposit(int AccountNum, double deposit) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "UPDATE ACCOUNTS SET BALANCE = BALANCE +" +deposit +"WHERE ACCOUNT_ID="+AccountNum;
							
			PreparedStatement stmt = con.prepareStatement(query);

			
			stmt.executeUpdate();
			stmt.close();

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void MakeWithdrawal(int accountNum, double withdrawal) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "UPDATE ACCOUNTS SET BALANCE = BALANCE -" +withdrawal +"WHERE ACCOUNT_ID="+accountNum;;
							
			PreparedStatement stmt = con.prepareStatement(query);
			
			
			stmt.executeUpdate();
			stmt.close();

			} catch(SQLIntegrityConstraintViolationException reason ) {
				
				System.out.println("WARNING! YOU ATTEMPTED TO WITHDRAW MORE MONEY THAN WAS AVAILABLE IN YOUR ACCOUNT! \n \n");
				System.out.println("Here at KB banking, we protect customers from overdraft fees by not allowing them \n \n");
				System.out.print("Just to be clear: You did NOT make a");
				
			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

	@Override
	public List<Accounts> getAccounts(int num) {
		
		List<Accounts> l1 = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String sql = "SELECT * FROM ACCOUNTS WHERE USER_ID=?";
			PreparedStatement stmt = con.prepareCall(sql);
			stmt.setInt(1, num);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int userId = rs.getInt("USER_ID");
				int accountsid = rs.getInt("ACCOUNT_ID");
				String accounttype = rs.getString("ACCOUNT_TYPE");
				double balance = rs.getDouble("BALANCE");
				
				l1.add(new Accounts(accountsid, accounttype, balance, userId));
			}
		}catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return l1;
		
		
		
	}

	@Override
	public boolean Login(String userlogin, String passlogin) {
		ResultSet rs = null;
		boolean login = false;
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "Select USER_ID FROM LEDGER WHERE USERNAME = ? AND PASS = ?";
			
		
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, userlogin);
			stmt.setString(2, passlogin);
			
			
			stmt.executeUpdate();
			rs= stmt.executeQuery();
			if (rs.next()==true) {
				login=true;
			}
			

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
		return login;
		
	}
	

	@Override
	public void deleteAccounts(int accountid) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int x=0;
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNT_ID=?";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, accountid);
			
			
			rs=stmt.executeQuery();
			
			rs.next();
			x =rs.getInt("BALANCE");
			stmt.close();
			rs.close();
			
			if (x==0) {
				String str = "DELETE FROM ACCOUNTS WHERE ACCOUNT_ID=?";
				stmt = con.prepareStatement(str);
				stmt.setInt(1, accountid);
				
				
				rs=stmt.executeQuery();
				rs.close();
				stmt.close();
			} else {
				System.out.println("You must withdraw all your money before deleting your account!");
			}
			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	@Override
	public Ledger getLedgerByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int retrieveUserAccounts(String userlogin, String passlogin) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int x=0;
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "SELECT * FROM LEDGER WHERE USERNAME=? AND PASS=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, userlogin);
			stmt.setString(2, passlogin);
			
			rs=stmt.executeQuery();
			
			rs.next();
			x =rs.getInt("USER_ID");
			stmt.close();
			rs.close();
			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
		
		return x;
	}

	@Override
	public Accounts accountAfterTransaction(int accountNum) {
		Accounts acc= new Accounts();
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String sql = "SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
			PreparedStatement stmt = con.prepareCall(sql);
			stmt.setInt(1, accountNum);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int userId = rs.getInt("USER_ID");
				int accountsid = rs.getInt("ACCOUNT_ID");
				String accounttype = rs.getString("ACCOUNT_TYPE");
				double balance = rs.getDouble("BALANCE");
				
				 acc = new Accounts(userId, accounttype, balance,accountsid);
				return acc;
			}
		}catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return acc;
	}

	@Override
	public boolean ItsNotYourAccount(int accountNum, int Userid) {
		ResultSet rs = null;
		boolean yourAccount = false;
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID=? AND USER_ID=?";
			
		
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, accountNum);
			stmt.setInt(2, Userid);
			
			
			stmt.executeUpdate();
			rs= stmt.executeQuery();
			if (rs.next()==true) {
				yourAccount=true;
			}
			

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
		return yourAccount;



	
	}

	@Override
	public void DeleteYoAccount(int Account_id) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "CALL SP_DELETE_YO_ACCOUNT(?)";
			
		
			CallableStatement cs = con.prepareCall(query);
			cs.setInt(1, Account_id);
			cs.execute();
			
			
			
			

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		

	}

	@Override
	public void SuperDeleteAllAccounts() {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "CALL SP_DELETE_ALL_ACCOUNTS";
			
		
			CallableStatement cs = con.prepareCall(query);
			cs.execute();
			
			
			
			

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int retrieveAccountBalance(int accountid) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int x=0;
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, accountid);
			
			
			rs=stmt.executeQuery();
			
			rs.next();
			x =rs.getInt("Balance");
			stmt.close();
			rs.close();
			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
		
		return x;
		
	}



	@Override
	public void SuperUpdateFirstName(String firstname, int userid) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "UPDATE LEDGER SET FIRSTNAME = ? WHERE USER_ID= ? ";
							
			PreparedStatement stmt = con.prepareStatement(query);
			
			stmt.setString(1, firstname);
			stmt.setInt(2, userid);
			
			stmt.executeUpdate();
			stmt.close();

			} 
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void SuperUpdateLastName(String lastname, int userid) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "UPDATE LEDGER SET LASTNAME = ? WHERE USER_ID= ? ";
							
			PreparedStatement stmt = con.prepareStatement(query);
			
			stmt.setString(1, lastname);
			stmt.setInt(2, userid);
			
			stmt.executeUpdate();
			stmt.close();

			} 
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void SuperUpdateUserName(String username, int userid) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "UPDATE LEDGER SET USERNAME = ? WHERE USER_ID= ? ";
							
			PreparedStatement stmt = con.prepareStatement(query);
			
			stmt.setString(1, username);
			stmt.setInt(2, userid);
			
			stmt.executeUpdate();
			stmt.close();

			} 
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void SuperUpdatePassword(String password, int userid) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "UPDATE LEDGER SET PASS = ? WHERE USER_ID= ? ";
							
			PreparedStatement stmt = con.prepareStatement(query);
			
			stmt.setString(1, password);
			stmt.setInt(2, userid);
			
			stmt.executeUpdate();
			stmt.close();

			} 
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void SuperDeleteAMember(int user_id) {
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "CALL SP_DELETE_A_USER(?)";
			
		
			CallableStatement cs = con.prepareCall(query);
			cs.setInt(1, user_id);
			cs.execute();
			
			
			
			

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
				
	}

	@Override
	public Boolean MemberExists(int userid) {
		ResultSet rs = null;
		boolean login = false;
		try(Connection con = ConnectionUtil.getConnectionFromFile("//users//birhan//Documents//eclipse-workspace//kathrynbirhanproject//src//test//java//resources//Connection")){
			String query = "Select * FROM LEDGER WHERE USER_ID = ?";
			
		
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, userid);
			
			
			stmt.executeUpdate();
			rs= stmt.executeQuery();
			if (rs.next()==true) {
				login=true;
			}
			

			}
		 catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
		return login;
	}

	

	
	
	
	
	
	
	
}
