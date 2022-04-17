package com.Brian.client;

import java.util.Scanner;
import java.util.Set;

import com.Brian.model.Account;
import com.Brian.model.Admin;
import com.Brian.model.Customer;
import com.Brian.model.Employee;
import com.Brian.model.Hotdog;
import com.Brian.model.User;
import com.Brian.respository.HotdogRespositoryImp;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class AppUI {
	
	
	public static void printWelcomeMenu() {
		System.out.println("Welcome to the Dog House\n"
				+ "Please Log in or register!\n"
				+ "1.) Login\n"
				+ "2.) Register\n"
				+ "3.) Exit");
	}
	
	public static void sayBye() {
		System.out.println("See you again soon!");
	}
	
	public static int handleUserSelection(Scanner scanner) {
		int userSelection = 0;
		try {
			userSelection = scanner.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Sorry, that is not a valid number.");
		}
		scanner.nextLine(); 
		return userSelection;
	}
	
	public static User loginUser(Scanner scanner) {
		String userName;
		String password;
		// Add validation for login. Check credentials.
		System.out.println("Enter your user information: ");
		System.out.println("User Name: ");
		userName = scanner.nextLine();

		System.out.println("Password: ");
		password = scanner.nextLine();
		User user = User.findOne(userName, password);
		
		if (user.accessLevel == 1) {
			user = new Customer(user.getUserId(), user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getStreet(),
					user.getCity(),  user.getState(), user.getZip());
			
		}
		else if (user.getAccessLevel() == 3) {
			user = new Employee(user.getUserId(), user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getStreet(),
					user.getCity(),  user.getState(), user.getZip());
		}
		else if (user.getAccessLevel() == 4) {
			user = new Admin(user.getUserId(), user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getStreet(),
					user.getCity(),  user.getState(), user.getZip());
		}
		return user;
	}
	
	public static void mainMenu(Scanner scanner, User user) {
		Customer customer = (Customer) user;
		printCustomerMain(customer);
		int userSelection =AppUI.handleUserSelection(scanner);
		
		switch (userSelection) {
		case 1:
			AppUI.printOwnedAccounts(customer, scanner);
			break;
		case 2:
			AppUI.createAccountMenu(scanner, customer);
			AppUI.mainMenu(scanner, user);
			break;
		case 3:
			System.out.println("xfer balance");
			scanner.nextLine();
			break;
		case 4:
			System.out.println("close accounts stuff");
			scanner.nextLine();
			break;
		case 5:
			AppUI.sayBye();
			break;
		default:
			break;
		}
	}
	
	public static void createAccountMenu(Scanner scanner, Customer customer) {
		Account account = new Account();
		System.out.println("Enter your account information: ");
		System.out.println("Starting Funds: ");
		account.setFunds(scanner.nextFloat());
		account.setOwnerId(customer.getUserId());
		account.save();
	}
	
	public static Employee loginEmployee(Scanner scanner) {
		Employee employee = new Employee();
		// Add validation for login. Check credentials.
		System.out.println("Enter your new user account information: ");
		System.out.println("User Name: ");
		employee.setUserName(scanner.nextLine());
		System.out.println("Password: ");
		employee.setPassword(scanner.nextLine());
		
		
		
		
		return employee;
	}
	
	public static Customer createCustomerAccount(Scanner scanner) {
		/*
		 * Yes, you should be doing some exception and validating the user inputs.
		 */
		Customer customer = new Customer();
		System.out.println("Enter your new user account information: ");
		System.out.println("User Name: ");
		customer.setUserName(scanner.nextLine());
		System.out.println("Password: ");
		customer.setPassword(scanner.nextLine());
		System.out.println("First Name: ");
		customer.setFirstName(scanner.nextLine());
		System.out.println("Last Name: ");
		customer.setLastName(scanner.nextLine());
		System.out.println("Street Address: ");
		customer.setStreet(scanner.nextLine());
		System.out.println("City: ");
		customer.setCity(scanner.nextLine());
		System.out.println("State: ");
		customer.setState(scanner.nextLine());
		System.out.println("5 digit Zip code: ");
		customer.setZip(scanner.nextInt());
		
		customer.save(customer);

		return customer;
	}
	
	public static void printCustomerMain(User user) {
		System.out.println("Welcome "+ user.getFirstName() + "\n"
				+"What would you like to do?\n"
				+ "1.) Accounts\n"
				+ "2.) Create an Account\n"
				+ "3.) Transfer Balance\n"
				+ "4.) Close Account\n"
				+ "5.) Exit");
	}
	
	public static Account createAccount(Customer customer, float funds) {
		Account account = new Account();
		account.setFunds(funds);
		account.setOwnerId(customer.getUserId());
		account.save();
		AppUI.printCustomerMain(customer);
		return account;
	}
	
	public static void printOwnedAccounts(Customer customer, Scanner scanner) {
		ArrayList<Account> accounts =Customer.findAllOwned(customer);
		int listNum = 1;
		System.out.println("Accounts Owned:");
		for(Account account: accounts) {
			System.out.println("[ "+listNum+" ID:" +account.getAccountId()+", Funds: "+ account.getFunds()+" ]");
			listNum++;
		}
		System.out.println("Please select an Account by List#");
		int userSelection = handleUserSelection(scanner);
		Account selectedAccount = accounts.get(userSelection - 1);
		AppUI.accountMenu(customer, scanner, selectedAccount);
	}
	
	public static void accountMenu(Customer customer, Scanner scanner, Account account) {
		System.out.println("Welcome "+ customer.getFirstName() + "\n"
				+"What would you like to do?\n"
				+ "1.) Order Hotdogs!\n"
				+ "2.) Add Funds\n"
				+ "3.) Remove Funds\n"
				+ "4.) Add Secondary User"
				+ "5.) Exit");
		int userSelection = handleUserSelection(scanner);
		switch (userSelection) {
		case 1:
			AppUI.printHotdogs();
			break;
		case 2:
			// adding funds
			break;
		case 3:
			// close this account
			scanner.nextLine();
			break;
		case 4:
			// add secondary user menu
		case 5:
			// byebye
			scanner.nextLine();
			break;

		default:
			break;
		}
	}
	
	public static void printHotdogs() {
		HotdogRespositoryImp hotdogRepo = new HotdogRespositoryImp();
		ArrayList<Hotdog> hotdogs = hotdogRepo.findAllHotdogs();
		for(Hotdog hotdog : hotdogs) {
			System.out.println(hotdog.toString());
		}
	}
	
	public static void orderMenu(Customer customer, Scanner scanner, Account account) {
		System.out.println(
				"What would you like to do?\n"
				+ "1.) Place Order\n"
				+ "2.) Nevermind, I changed my mind\n"
				+ "3.) Order a Sampler (1 of each)\n"
				+ "4.) Exit"
				);
		int userSelection = handleUserSelection(scanner);
		switch (userSelection) {
		case 1:
			// order
			break;
		case 2:
			// adding funds
			break;
		case 3:
			// close this account
			scanner.nextLine();
			break;
		case 4:
			AppUI.sayBye();
			scanner.close();
			break;

		default:
			break;
		}
	}
	
	

}
