package com.Brian.client;

import java.util.Scanner;
import java.util.Set;

import com.Brian.model.Account;
import com.Brian.model.Admin;
import com.Brian.model.Customer;
import com.Brian.model.Employee;
import com.Brian.model.Hotdog;
import com.Brian.model.ManagerList;
import com.Brian.model.User;
import com.Brian.respository.HotdogRespositoryImp;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class AppUI {
	
	public static void appStart() {
		
		Scanner scanner = new Scanner(System.in);
		boolean isUserInterested = true;
		HotdogRespositoryImp newRepo = new HotdogRespositoryImp();
		ArrayList<Hotdog> hotdogs = newRepo.findAllHotdogs();
		int userSelection;

		
		
			AppUI.printWelcomeMenu();
			try {
				while (isUserInterested) {
				userSelection = AppUI.handleUserSelection(scanner);
				switch (userSelection) {
				case 1:
					User user = AppUI.loginUser(scanner);
					
					if( user != null && user.getAccessLevel() == 1) {
						AppUI.mainMenu(scanner, user);
					}
					else if ( user != null && user.getAccessLevel() == 3) {

						AppUIEmployee.employeeMain(user, scanner);
					}
					else if ( user != null && user.getAccessLevel() == 4) {
						
						AppUIEmployee.adminMain(user, scanner);
					}
					else {
						
						System.out.println("Incorrect User Information");
						appStart();
					}
					
					break;
				case 2:
					AppUI.createCustomerAccount(scanner);
					break;
				case 3:
					AppUI.sayBye();
					isUserInterested = false;
					break;
				default:
					break;
				}
			}
			scanner.close();
			}
			catch(Exception exception){
				
			}
			
	}

	
	
	
	
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
	public static float handleUserFloat(Scanner scanner) {
		float userInput = 0;
		try {
			userInput = scanner.nextFloat();
		}catch(InputMismatchException e) {
			System.out.println("Sorry, that is not a valid number.");
		}
		scanner.nextLine(); 
		return userInput;
	}
	
	public static User loginUser(Scanner scanner) {
		// user input
		String userName;
		String password;
		// prompts
		System.out.println("Enter your user information: ");
		System.out.println("User Name: ");
		userName = scanner.nextLine();

		System.out.println("Password: ");
		password = scanner.nextLine();
		
		// sql query. returns an instance of user if username is found.  null if it is not.
		User user = User.findOne(userName, password);
		// If else statements for checking user.accessLevel to direct to proper set of menus.
		return user;
		}
	
	
	public static void mainMenu(Scanner scanner, User user) {
		Customer customer = new Customer(user);
		ArrayList<Account> accounts =Customer.findAllOwned(customer);
		System.out.println("Accounts Owned:");
		for(Account account: accounts) {
			System.out.println("[ "+" ID:" +account.getAccountId()+", Funds: "+ account.getFunds()+"  ]");
			
		}
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
			AppUI.transferBalance(customer, scanner);
			break;
		case 4:
			AppUI.manageMenu(customer, scanner);
			break;
		case 5:
			AppUI.sayBye();
			AppUI.appStart();
			break;
		default:
			System.out.println("Not a valid selection");
			AppUI.mainMenu(scanner, user);
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
	
	public static Employee loginEmployee(User user, Scanner scanner) {
		Employee employee = new Employee(user);
		
		
		
		
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
				+ "4.) Manage other accounts\n"
				+ "5.) Main Menu");
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
		if (userSelection < 1 || userSelection > accounts.size()) {
			System.out.println("Invalid #, please try again.");
			AppUI.printOwnedAccounts(customer, scanner);
		}
		else {
			Account selectedAccount = accounts.get(userSelection - 1);
			AppUI.accountMenu(customer, scanner, selectedAccount);
		}

	}
	
	public static void accountMenu(Customer customer, Scanner scanner, Account account) {
		System.out.println(account.toString());
		System.out.println("Welcome "+ customer.getFirstName() + "\n"
				+"What would you like to do?\n"
				+ "1.) View Hotdogs!\n"
				+ "2.) Add Funds\n"
				+ "3.) Remove Funds\n"
				+ "4.) Secondary Users\n"
				+ "5.) Main Menu");
		int userSelection = handleUserSelection(scanner);
		switch (userSelection) {
		case 1:
			AppUI.printHotdogs();
			AppUI.orderMenu(customer, scanner, account);
			break;
		case 2:
			System.out.println("How much would you like to add?");
			float addAmount = scanner.nextFloat();
			// adding funds
			account.addFunds(account, addAmount);
			AppUI.accountMenu(customer, scanner, account);
			break;
		case 3:
			System.out.println("How much would you like to remove?");
			float rmAmount = scanner.nextFloat();
			// reducing funds
			account.reduceFunds(account, rmAmount);
			AppUI.accountMenu(customer, scanner, account);
			break;
		case 4:
			AppUI.secondaryUserMenu(customer, scanner, account);
			break;
		case 5:
			AppUI.sayBye();
			AppUI.appStart();
			break;

		default:
			System.out.println("Not a valid selection");
			AppUI.accountMenu(customer, scanner, account);
			break;
		}
	}
	
	public static void printHotdogs() {
		HotdogRespositoryImp hotdogRepo = new HotdogRespositoryImp();
		ArrayList<Hotdog> hotdogs = hotdogRepo.findAllHotdogs();
		int listNum = 1;
		for(Hotdog hotdog : hotdogs) {
			System.out.println("List Number: "+listNum + hotdog.toString());
			listNum++;
		}
	}
	
	public static void orderMenu(Customer customer, Scanner scanner, Account account) {
		HotdogRespositoryImp hotdogRepo = new HotdogRespositoryImp();
		ArrayList<Hotdog> hotdogs = hotdogRepo.findAllHotdogs();
		System.out.println(account.toString());
		System.out.println(
				"What would you like to do?\n"
				+ "1.) Order Hotdogs!\n"
				+ "2.) Nevermind, I changed my mind\n"
				+ "3.) Order a Sampler (1 of each)\n"
				+ "4.) Main Menu"
				);
		int userSelection = handleUserSelection(scanner);
		switch (userSelection) {
		case 1:
			AppUI.orderingHotdogs(customer, scanner, account, hotdogs);
			break;
		case 2:
			AppUI.accountMenu(customer, scanner, account);
			break;
		case 3:

			float sum = 0;
			for(Hotdog hotdog : hotdogs) {
				sum += hotdog.getCost();
			}
			System.out.println("Total cost will be: $" +sum+"\n"
					+ "Would you like to proceed?\n"
					+ "1.) Yes\n"
					+ "2.) No");
			int confirm = handleUserSelection(scanner);
			switch (confirm) {
			case 1:
				account.reduceFunds(account, sum);
				System.out.println("Thank you for your order!");
				System.out.println(account.toString());
				AppUI.orderMenu(customer,scanner,account);
				break;
			case 2:
				AppUI.orderMenu(customer, scanner, account);
				break;
			}
			break;
		case 4:
			AppUI.sayBye();
			AppUI.appStart();
			break;

		default:
			System.out.println("Not a valid selection");
			AppUI.orderMenu(customer, scanner, account);
			break;
		}
	}
	
	public static void secondaryUserMenu(Customer customer, Scanner scanner, Account account ) {
				account.setManagers(ManagerList.findAllManagers(account));
				for (Customer manager : account.getManagers()) {
					System.out.println(
						manager.toStringSafe()
					);
				}
				
				System.out.println(
				"What would you like to do?\n"
				+ "1.) Add a Secondary User\n"
				+ "2.) Remove a Secondary User\n"
				+ "3.) Return to Previous Menu\n"
				+ "4.) Main Menu"
				);
				int userSelection = handleUserSelection(scanner);
				switch (userSelection) {
				case 1:
					System.out.println("Please type the user's name:");
					String username = scanner.nextLine(); // taking user input for username
					// validate user input by checking against data base.
					Customer secondUser = Customer.findByUserName(username);
					if(secondUser != null) {
						account.addManager(secondUser);
						AppUI.secondaryUserMenu(customer, scanner, account);
					}else {
						System.out.println("User does not exist. Check for accuracy");
						AppUI.secondaryUserMenu(customer, scanner, account);
					}
					
					
					break;
				case 2:
					System.out.println("Please type the user's name:");
					String username2 = scanner.nextLine(); // taking user input for username
					// validate user input by checking against data base.
					Customer secondUser2 = Customer.findByUserName(username2);
					if(secondUser2 != null) {
						account.removeManager(secondUser2);
						AppUI.secondaryUserMenu(customer, scanner, account);
					}else {
						System.out.println("User does not exist. Check for accuracy");
						AppUI.secondaryUserMenu(customer, scanner, account);
					}
					break;
				case 3:
					AppUI.accountMenu(customer, scanner, account);
					break;
				case 4:
					AppUI.sayBye();
					AppUI.appStart();
					break;

				default:
					System.out.println("Not a valid selection");
					AppUI.secondaryUserMenu(customer, scanner, account);
					break;
				}
				
				
	}
	public static void orderingHotdogs(Customer customer, Scanner scanner, Account account, ArrayList<Hotdog> hotdogs) {
		System.out.println("Which Hotdog would you like to purchase? Type in List Number:");
		int hotdogSelection = handleUserSelection(scanner)-1;
		if (hotdogSelection+1 > hotdogs.size() || hotdogSelection < 0) {
			System.out.println("Not a valid number, please try again.");
		}
		else {
			System.out.println("How many would you like?");
			int quantity = handleUserSelection(scanner);
			if(quantity > 0 ) {
				float totalCost = hotdogs.get(hotdogSelection).getCost() * quantity;
				account.reduceFunds(account, totalCost);
				AppUI.orderMenu(customer, scanner, account);
			}
			else {
				System.out.println("Not a valid quantity, returning to order menu.");
				AppUI.orderMenu(customer, scanner, account);
			}
		}
	}
	
	public static void transferBalance(Customer customer, Scanner scanner) {
		ArrayList<Account> accounts = Customer.findAllOwned(customer);
		if (accounts.size() < 2) {
			System.out.println("You don't own more than 1 account and funds transfer is unavailable.");
			AppUI.mainMenu(scanner, customer);
		}
		int listNum = 1;
		System.out.println("Accounts Owned:");
		for(Account account: accounts) {
			System.out.println("[ "+listNum+" ID:" +account.getAccountId()+", Funds: "+ account.getFunds()+" ]");
			listNum++;
		}
		System.out.println("Please select an Account by List#");
		int userSelection = handleUserSelection(scanner);

		if (userSelection < 1 || userSelection > accounts.size()) {
			System.out.println("Invalid #, please try again.");
			AppUI.printOwnedAccounts(customer, scanner);
		}
		else {
			Account selectedAccount = accounts.get(userSelection - 1);
			System.out.println("How much would you like to transfer from this account?");
			float totalAmount = handleUserFloat(scanner);
			accounts.remove(userSelection-1);
			int listNum2 = 1;
			for(Account account: accounts) {
				System.out.println("[ "+listNum2+" ID:" +account.getAccountId()+", Funds: "+ account.getFunds()+" ]");
				listNum++;
			}
			System.out.println("Which account would you like the money transferred to?");
			int userSelection2 = handleUserSelection(scanner);
			if (accounts.size() < 1) {
				AppUI.mainMenu(scanner, customer);
			}
			else if (userSelection2 < 1 || userSelection2 > accounts.size()) {
				listNum2 = 1;
				System.out.println("Invalid #, please try again.");
				for(Account account: accounts) {
					System.out.println("[ "+listNum2+" ID:" +account.getAccountId()+", Funds: "+ account.getFunds()+" ]");
					listNum++;
				}
			}
			else {
				Account otherAccount = accounts.get(userSelection2-1);
				selectedAccount.reduceFunds(selectedAccount, totalAmount);
				otherAccount.addFunds(otherAccount, totalAmount);
				AppUI.mainMenu(scanner, customer);
			}
		}
		
	}
	public static void manageMenu(Customer customer, Scanner scanner) {
		ArrayList<Account> managedAccounts = ManagerList.findAllManaged(customer);
		int listNum = 1;
		for(Account account : managedAccounts) {
			System.out.println("List# "+listNum+" "+account.toString());
			listNum++;
		}
		System.out.println("Which account would you like to use?");
		
		int userSelection =AppUI.handleUserSelection(scanner);
		
		if (userSelection < 1 || userSelection > managedAccounts.size()) {
			System.out.println("Invalid #, please try again.");
			manageMenu(customer, scanner);
		}
		else {
			Account selectedAccount = managedAccounts.get(userSelection - 1);
			AppUI.orderMenuManager(customer, scanner, selectedAccount);
		}
		
	}
	
	public static void orderMenuManager(Customer customer, Scanner scanner, Account account) {
		HotdogRespositoryImp hotdogRepo = new HotdogRespositoryImp();
		ArrayList<Hotdog> hotdogs = hotdogRepo.findAllHotdogs();
		System.out.println(account.toString());
		System.out.println(
				"What would you like to do?\n"
				+ "1.) Order Hotdogs!\n"
				+ "2.) Nevermind, I changed my mind\n"
				+ "3.) Order a Sampler (1 of each)\n"
				+ "4.) Main Menu"
				);
		int userSelection = handleUserSelection(scanner);
		switch (userSelection) {
		case 1:
			AppUI.orderingHotdogs(customer, scanner, account, hotdogs);
			break;
		case 2:
			AppUI.mainMenu(scanner, customer);
			break;
		case 3:

			float sum = 0;
			for(Hotdog hotdog : hotdogs) {
				sum += hotdog.getCost();
			}
			System.out.println("Total cost will be: $" +sum+"\n"
					+ "Would you like to proceed?\n"
					+ "1.) Yes\n"
					+ "2.) No");
			int confirm = handleUserSelection(scanner);
			switch (confirm) {
			case 1:
				account.reduceFunds(account, sum);
				System.out.println("Thank you for your order!");
				AppUI.orderMenuManager(customer,scanner,account);
				break;
			case 2:
				AppUI.orderMenuManager(customer, scanner, account);
				break;
			}
			break;
		case 4:
			AppUI.sayBye();
			AppUI.appStart();
			break;

		default:
			System.out.println("Not a valid selection");
			AppUI.orderMenuManager(customer, scanner, account);
			break;
		}
	}
	
	

}
