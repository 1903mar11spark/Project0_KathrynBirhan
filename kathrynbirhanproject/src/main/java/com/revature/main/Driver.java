package com.revature.main;

import java.util.InputMismatchException;
import java.util.List;

import com.revature.beans.Accounts;
import com.revature.beans.Ledger;
import com.revature.dao.BankDAO;
import com.revature.dao.BankDaoImplementation;
import java.util.Scanner;
public class Driver {


public static void main(String[] args) {
	
	BankDAO bd =  new BankDaoImplementation();
	
	
	
	

	try{
	System.out.println("Thank you for choosing KB Banking!\n ");
	
	System.out.println("Enter '1' into the console to register as new a Member ");
	System.out.println("Enter '2' into the console to login \n");
	System.out.println("Enter any other number to quit the KB Banking experience\n");
	System.out.println("NOTE: PLEASE ONLY ENTER NUMERICAL VALUES WHEN INSTRUCTED TO DO SO");
	Scanner myObj = new Scanner(System.in);
	int choose = myObj.nextInt();
	
	switch(choose) {
	//Create A Bank Membership
	case 1:
		System.out.println("Enter your Desired Username\n");
		String uname=myObj.next();
		System.out.println("Enter your Desired password\n");
		String pass=myObj.next();
		System.out.println("Enter your First Name\n");
		String fname=myObj.next();
		System.out.println("Enter your Last Name\n");
		String lname=myObj.next();
		myObj.close();
		Ledger led = new Ledger(uname,pass,fname,lname);
		bd.CreateNewUser(led);
		break;
		//LogIn
	case 2:
		System.out.println("enter your username");
		String userlog = myObj.next();
		System.out.println("enter your password");
		String passlog = myObj.next();
		int pick;
		
		
		
		/*
		 * this part is for the SuperUser
		 */
		if(userlog.equals("shameless") && passlog.equals("allDylansAreCool")) { 
			int supes;
			
			do{
			System.out.println("Welcome SuperUser!\n");
				System.out.println("1. view all accounts");
				System.out.println("2. create a Member");
				System.out.println("3. Update a Member");
				System.out.println("4. Delete a Member");
				System.out.println("5. Delete all Accounts");
				System.out.println("6. Logout");
				supes = myObj.nextInt();
			
				switch(supes) {
				
				
				case 1:
					/*
					 * view all accounts
					 */
					System.out.println("A list of all Members");
					List<Ledger> ClientList = bd.getLedger();
				
					for(Ledger l : ClientList) {
						System.out.println(l);
					}
					System.out.println("\n\n");
					break;
					

					
				case 2:
					/*
					 * create a member
					 */
					System.out.println("Enter the Desired Username\n");
					String suname=myObj.next();
					System.out.println("Enter the Desired password\n");
					String spass=myObj.next();
					System.out.println("Enter the First Name\n");
					String sfname=myObj.next();
					System.out.println("Enter the Last Name\n");
					String slname=myObj.next();
					myObj.close();
					Ledger sled = new Ledger(suname,spass,sfname,slname);
					bd.CreateNewUser(sled);
					break;
					
					
					
					
				case 3:
					/*
					 * update a member
					 */
					int update;
					System.out.println("Enter a number 1-4 corresponding number for the field you would like to update\n");
					System.out.println("1. Update Username");
					System.out.println("2. Update Password");
					System.out.println("3. Update First Name");
					System.out.println("4. Update Last Name");
					update=myObj.nextInt();
					switch(update) {
					case 1:
						/*
						 * Update Username
						 */
						int userid;
						String Username;
						System.out.println("Enter the User ID for the member whose username you would like to update");
						userid=myObj.nextInt();
						
							if (!bd.MemberExists(userid)) {
								System.out.println("There is no Member Associated with that User ID! try another \n\n");
								break;
							}
						
						
						System.out.println("Enter the new Username");
						Username=myObj.next();
						bd.SuperUpdateUserName(Username, userid);
						System.out.println("Username has been updated for user "+userid +" Unless there is a message above indicating it is already in use");

						break;
					case 2:
						/*
						 * Update Password
						 */
						String password;
						String check;
						System.out.println("Select the User ID for the member you would like to update");
						userid=myObj.nextInt();
							if (!bd.MemberExists(userid)) {
								System.out.println("There is no Member Associated with that User ID! try another \n\n");
								break;
							}
						System.out.println("Enter your new Password");
						password= myObj.next();
						System.out.println("Enter your new Password once more for confirmation");
						check=myObj.next();
							if(password.equals(check)) {
								bd.SuperUpdatePassword(password, userid);
							System.out.println("Password has been updated for user "+userid);
							} else {
								System.out.println("passwords did not match, please try again!");
							}
						break;
					case 3:
						/*
						 * Update first name
						 */
						String firstname;
						
						System.out.println("Select the User ID for the member you would like to update");
						userid=myObj.nextInt();
						
							if (!bd.MemberExists(userid)) {
								System.out.println("There is no Member Associated with that User ID! try another \n\n");
								break;
							}
						
						
						System.out.println("Enter the desired first name");
						firstname=myObj.next();
						
						bd.SuperUpdateFirstName(firstname, userid);
						System.out.println("First Name has been updated for user "+userid);
						break;
					case 4:
						
						/*
						 * Update lastname
						 */
						
						
						String lastname;
						
						System.out.println("Select the User ID for the member you would like to update");
						userid=myObj.nextInt();
						
							if (!bd.MemberExists(userid)) {
								System.out.println("There is no Member Associated with that User ID! try another \n\n");
								break;
							}
							
						System.out.println("Enter the desired last name");
						lastname=myObj.next();
						
						bd.SuperUpdateLastName(lastname, userid);
						System.out.println("Last Name has been updated for user "+userid);

						break;
					
					default:
						break;
					}
					break;
					
					
				case 4:
					/*
					 * Delete User
					 */
					
					int userid;
					System.out.println("enter the userid of the member you would like to delete");
					userid=myObj.nextInt();
					bd.SuperDeleteAMember(userid);
					System.out.println("The User has been deleted");
					break;
				case 5:
					/*
					 * Delete All Users (
					 */
					int delete;
					System.out.println("ARE YOU SURE YOU WANT TO DO THIS?");
					System.out.println("Enter '1' for yes");
					System.out.println("Enter '2' for no");
					delete= myObj.nextInt();
					if (delete ==1) {
						bd.SuperDeleteAllAccounts();
						System.out.println("All accounts have been deleted");
					} else {
						System.out.println("No Accounts have been deleted");
					}
					break;
				default:
					break;
				}
			}while(supes!=6);
		}
		
		
		
		
		
		

		
		else if (bd.Login(userlog,passlog)==true) {
			do{
			/*
			 * int userid=bd.retrieveUserAccounts(userlog, passlog);
			 */
			System.out.println("You are logged in! please choose from one of the following options by entering the number next to it \n\n");
			System.out.println("1.Create A Checking Account");
			System.out.println("2.Create A Savings Account");
			System.out.println("3.View Your an Account");
			System.out.println("4.Deposit");
			System.out.println("5.Withdraw");
			System.out.println("6.Delete an account");
			System.out.println("7. Logout \n");
			pick = myObj.nextInt();
			System.out.println("");
			
			
					switch(pick) {
					/*
					 * Create A checking Account
					 */
					case 1: 
						int userid=bd.retrieveUserAccounts(userlog, passlog);
						bd.CreateNewCheckingAccount(userid);
						System.out.println("Congrats you made a Checking Account");
						System.out.println(bd.getAccounts(userid));
						break;
					case 2:
						/*
						 * Create A savings Account
						 */
						userid=bd.retrieveUserAccounts(userlog, passlog);
						bd.CreateNewSavingsAccount(userid);
						System.out.println("Congrats you made a Savings Account");
						System.out.println(bd.getAccounts(userid));
						break;
					case 3:
						/*
						 * view your accounts
						 */
						userid=bd.retrieveUserAccounts(userlog, passlog);
						List<Accounts> YourAccounts = bd.getAccounts(userid);
						for(Accounts a: YourAccounts) {
							System.out.println(YourAccounts);
						}
						if (YourAccounts.isEmpty()) {
							System.out.println("You have no accounts! why not make one?\n\n");
						}
						break;
					case 4:
						/*
						 * make a deposit
						 */
						userid=bd.retrieveUserAccounts(userlog, passlog);
						System.out.println("Enter the Account Number of the Account you would like to make a deposit from into");
						int accountNum = myObj.nextInt();
						System.out.println("Enter your deposit amount");
						double deposit=myObj.nextInt();
						if(bd.ItsNotYourAccount(accountNum, userid)==true) {
						System.out.println("Account details before deposit: " +bd.accountAfterTransaction(accountNum)+"\n\n");
						bd.MakeDeposit(accountNum, deposit);
						System.out.println("Account details After deposit: " +bd.accountAfterTransaction(accountNum)+"\n\n");
						System.out.println("you made a deposit of: $" + deposit + " into Account " + accountNum);}
						else {
							System.out.println("That Account does not belong to you!");
						}
						break;
					case 5:
						/*
						 * make a withdrawal
						 */
						userid=bd.retrieveUserAccounts(userlog, passlog);
						System.out.println("Enter the Account Number of the Account you would like to make a withdrawal from\n\n");
						int accountNumw = myObj.nextInt();
						System.out.println("Enter your withdrawal amount\n\n");
						double withdrawal=myObj.nextInt();
						if(bd.ItsNotYourAccount(accountNumw, userid)==true) {
						System.out.println("Account details before withdrawal: " +bd.accountAfterTransaction(accountNumw)+"\n\n");
						bd.MakeWithdrawal(accountNumw, withdrawal);
						System.out.println(" Withdrawal of: $"+withdrawal+ " from Account " + accountNumw +"\n\n");
						System.out.println("Account details After withdrawal: " +bd.accountAfterTransaction(accountNumw));
						}
						else {
							System.out.println("That Account does not belong to you!");
							System.out.println("You cannot withdraw from an account does not belong to you!");
						}
						break;
					case 6:
						/*
						 * delete an account
						 */
					
						userid=bd.retrieveUserAccounts(userlog, passlog);
						int accountNumd = myObj.nextInt();
						System.out.println("Enter the Account number you would like to delete");
						if(bd.ItsNotYourAccount(accountNumd, userid)==true && bd.retrieveAccountBalance(accountNumd)==0) {
							bd.DeleteYoAccount(accountNumd);
							System.out.println("Your Account has been deleted\n\n If you have any remaining accounts, they will be listed bellow");
							
							userid=bd.retrieveUserAccounts(userlog, passlog);
							List<Accounts> remainingAccounts = bd.getAccounts(userid);
							for(Accounts a: remainingAccounts) {
								System.out.println(remainingAccounts);
							}
						}
						break;
					case 7:
						break;
					default:
						System.out.println("PLEASE ONLY ENTER NUMERICAL VALUES BETWEEN 1-7...anything else would fit Einstein's definition of insanity \n \n");
					}

		} while(pick!=7);
			}else {
						System.out.println("Sorry Wrong Username and Password!");
						}
			
			
		
			
			
			
			
			
			
		 

		break;
	default:
		System.out.println("Please only enter either '1' or '2' or '3'");
		break;
		
	}
	
	
	
} catch (InputMismatchException e) {
	System.out.println("PLEASE FOLLOW DIRECTIONS AND ONLY ENTER NUMERICAL RESPONSES WHEN PROMTPTED TO DO SO");
}
	
	

	
	
}





}
	
	
	
	
	

	
	
	
	
	
	
	

