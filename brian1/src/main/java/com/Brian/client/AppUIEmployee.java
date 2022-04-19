package com.Brian.client;

import java.util.ArrayList;
import java.util.Scanner;

import com.Brian.Driver;
import com.Brian.model.Account;
import com.Brian.model.Admin;
import com.Brian.model.Customer;
import com.Brian.model.Employee;
import com.Brian.model.ManagerList;
import com.Brian.model.User;

public class AppUIEmployee {
	public static void printEmployeeMain(User employee) {
		System.out.println(
				"What would you like to do?\n"
				+ "1.) See all Accounts\n"
				+ "2.) See all Users\n"
				+ "3.) See all Secondary Users for an Account\n"
				+ "4.) See all Accounts a User is Secondary User\n"
				+ "5.) Cancel Account\n"
				+ "6.) Remove User\n"
				+ "7.) Main Menu");
	}
	
	public static void printAdminMain(User admin) {
		System.out.println(
				"What would you like to do?\n"
				+ "1.) See all Accounts\n"
				+ "2.) See all Users\n"
				+ "3.) See all Secondary Users for an Account\n"
				+ "4.) See all Accounts a User is Secondary User\n"
				+ "5.) Cancel Account\n"
				+ "6.) Remove User\n"
				+ "7.) Modify Account Balance\n"
				+ "8.) Modify User Information\n"
				+ "9.) Main Menu");
	}
	
	public static void printUpdateMenu() {
		System.out.println(
				"Which field would you like to change??\n"
				+ "1.) Username \n"
				+ "2.) Password \n"
				+ "3.) First name \n"
				+ "4.) Last name \n"
				+ "5.) Street address \n"
				+ "6.) City \n"
				+ "7.) State \n"
				+ "8.) Zip code\n"
				+ "9.) Return to admin menu");
	}
	
	
	public static void employeeMain(User employee, Scanner scanner) {
		printEmployeeMain(employee);
		int userSelection =AppUI.handleUserSelection(scanner);
		
		switch (userSelection) {
		case 1:
			ArrayList<Account> accounts = Account.findAll();
			for (Account account : accounts) {
				System.out.println(account.toStringEmp());
			}
			employeeMain(employee, scanner);
			break;
		case 2:
			ArrayList<User> users = User.findAll();
			for (User user : users) {
				System.out.println(user.toString());
			}
			employeeMain(employee, scanner);
			break;
		case 3:
			System.out.println("Type in account # to see its secondary users");
			int userSelection2 = AppUI.handleUserSelection(scanner);
			Account account = Account.findOne(userSelection2);
			if(account != null) {
				ArrayList<Customer> secondaryUsers = ManagerList.findAllManagers(account);
				if(secondaryUsers.size() > 0) {
					for (Customer customer : secondaryUsers) {
						System.out.println(customer.toStringSafe());
					}
				}else {
					System.out.println("Account has no secondary users.");
				}
			
			}else {System.out.println("Account not found");}
			employeeMain(employee, scanner);
			break;
		case 4:
			System.out.println("Type in user ID# to see accounts user has access to");
			int userSelection3 = AppUI.handleUserSelection(scanner);
			User user = User.findOneById(userSelection3);
			if(user != null) {
				ArrayList<Account> accountsManaged = ManagerList.findAllManagedUser(user);
				if(accountsManaged.size() > 0) {
					for (Account accountManaged : accountsManaged) {
						System.out.println(accountManaged.toString());
					}
				}else {
					System.out.println("User has no managed accounts.");
				}

			}else {System.out.println("User not found");}
			employeeMain(employee, scanner);
			break;
		case 5:
			System.out.println("Please type in the account # you wish to close");
			int userSelection4 = AppUI.handleUserSelection(scanner);
			Account selectedAccount = Account.findOne(userSelection4);
			Account.closeAccount(selectedAccount);
			System.out.println("Account successfully closed");
			employeeMain(employee, scanner);
			break;
		case 6:
			System.out.println("Please type in the User ID# you wish to close");
			int userSelection5 = AppUI.handleUserSelection(scanner);
			User selectedUser = User.findOneById(userSelection5);
			User.removeUserImp(selectedUser);
			System.out.println("User successfully removed");
			employeeMain(employee, scanner);
			break;
		case 7:
			AppUI.sayBye();
			break;
		default:
			System.out.println("Not a valid selection");
			employeeMain(employee, scanner);
			break;
		}
	}
	
	public static void adminMain(User employee, Scanner scanner) {
		printAdminMain(employee);
		int userSelection =AppUI.handleUserSelection(scanner);
		
		switch (userSelection) {
		case 1:
			ArrayList<Account> accounts = Account.findAll();
			for (Account account : accounts) {
				System.out.println(account.toStringEmp());
			}
			adminMain(employee, scanner);
			break;
		case 2:
			ArrayList<User> users = User.findAll();
			for (User user : users) {
				System.out.println(user.toString());
			}
			adminMain(employee, scanner);
			break;
		case 3:
			System.out.println("Type in account # to see its secondary users");
			int userSelection2 = AppUI.handleUserSelection(scanner);
			Account account = Account.findOne(userSelection2);
			if(account != null) {
				ArrayList<Customer> secondaryUsers = ManagerList.findAllManagers(account);
				if(secondaryUsers.size() > 0) {
					for (Customer customer : secondaryUsers) {
						System.out.println(customer.toStringSafe());
					}
					adminMain(employee, scanner);
				}else {
					System.out.println("Account has no secondary users.");
					adminMain(employee, scanner);
				}
			
			}else {System.out.println("Account not found");}
			adminMain(employee, scanner);
			break;
		case 4:
			System.out.println("Type in user ID# to see accounts user has access to");
			int userSelection3 = AppUI.handleUserSelection(scanner);
			User user = User.findOneById(userSelection3);
			if(user != null) {
				ArrayList<Account> accountsManaged = ManagerList.findAllManagedUser(user);
				if(accountsManaged.size() > 0) {
					for (Account accountManaged : accountsManaged) {
						System.out.println(accountManaged.toString());
					}
					adminMain(employee, scanner);
				}else {
					System.out.println("User has no managed accounts.");
					adminMain(employee, scanner);
				}

			}else {System.out.println("User not found");}
			adminMain(employee, scanner);
			break;
		case 5:
			System.out.println("Please type in the account # you wish to close");
			int userSelection4 = AppUI.handleUserSelection(scanner);
			Account selectedAccount = Account.findOne(userSelection4);
			Account.closeAccount(selectedAccount);
			System.out.println("Account successfully closed");
			adminMain(employee, scanner);
			break;
		case 6:
			System.out.println("Please type in the User ID# you wish to close");
			int userSelection5 = AppUI.handleUserSelection(scanner);
			User selectedUser = User.findOneById(userSelection5);
			User.removeUserImp(selectedUser);
			System.out.println("User successfully removed");
			adminMain(employee, scanner);
			break;
		case 7:
			System.out.println("Provide Account ID# :");
			int adminSelection = AppUI.handleUserSelection(scanner);
			Account chosenAccount = Account.findOne(adminSelection);
			if(chosenAccount != null) {
				System.out.println("1.) Add funds\n"
						+ "2.) Reduce funds");
				int optionSelect = AppUI.handleUserSelection(scanner);
				switch(optionSelect) {
				case 1:
					System.out.println("Enter amount");
					float addAmount = AppUI.handleUserFloat(scanner);
					chosenAccount.addFunds(chosenAccount, addAmount);
					System.out.println(chosenAccount.toString());
					adminMain(employee, scanner);
					break;
				case 2:
					System.out.println("Enter amount");
					float redAmount = AppUI.handleUserFloat(scanner);
					chosenAccount.reduceFunds(chosenAccount, redAmount);
					System.out.println(chosenAccount.toString());
					adminMain(employee, scanner);
					break;
				default:
					adminMain(employee, scanner);
					break;
				}
			}
			else {
				System.out.println("Account not found");
				adminMain(employee, scanner);
			}

			break;
		case 8:
			updateMenu(employee, scanner);
			break;
		case 9:
			AppUI.sayBye();
			break;
		default:
			System.out.println("Not a valid selection");
			adminMain(employee, scanner);
			break;
		}
		AppUI.appStart();
	}
	
	public static void updateMenu(User admin, Scanner scanner) {
		System.out.println("Enter user id# : ");
		int userId = AppUI.handleUserSelection(scanner);			
		User chosenUser = User.findOneById(userId);
		printUpdateMenu();
		int userSelection = AppUI.handleUserSelection(scanner);
		switch(userSelection) {
			case 1:
				System.out.println("Enter your change:");
				String username = scanner.nextLine();
				chosenUser.setUserName(username);
				break;
			case 2:
				System.out.println("Enter your change:");
				String password = scanner.nextLine();
				chosenUser.setPassword(password);
				break;
			case 3:
				System.out.println("Enter your change:");
				String firstName = scanner.nextLine();
				chosenUser.setFirstName(firstName);
				break;
			case 4:
				System.out.println("Enter your change:");
				String lastName = scanner.nextLine();
				chosenUser.setLastName(lastName);
				break;
			case 5:
				System.out.println("Enter your change:");
				String street = scanner.nextLine();
				chosenUser.setStreet(street);
				break;
			case 6:
				System.out.println("Enter your change:");
				String city = scanner.nextLine();
				chosenUser.setCity(city);
				break;
			case 7:
				System.out.println("Enter your change:");
				String state = scanner.nextLine();
				chosenUser.setState(state);
				break;
			case 8:
				System.out.println("Enter your change:");
				int zip = AppUI.handleUserSelection(scanner);
				chosenUser.setZip(zip);
				break;
			case 9:
				break;
			default:
				break;
		}
		User.updateUserInfo(chosenUser);
		System.out.println(chosenUser.toString());
		adminMain(admin, scanner);
	}
}
