package com.Brian.client;

import java.util.Scanner;

import com.Brian.model.Customer;
import com.Brian.model.Employee;

import java.util.InputMismatchException;

public class AppUI {
	
	
	public static void printWelcomeMenu() {
		System.out.println("Welcome to the Dog House\n"
				+ "Please Log in or register!\n"
				+ "1.) Customer Login\n"
				+ "2.) Employee Login\n"
				+ "3.) Customer Register\n"
				+ "4.) Employee Register\n"
				+ "5.) Exit");
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
	
	public static Customer loginCustomer(Scanner scanner) {
		Customer customer = new Customer();
		// Add validation for login. Check credentials.
		System.out.println("Enter your new user account information: ");
		System.out.println("User Name: ");
		customer.setUserName(scanner.nextLine());
		System.out.println("Password: ");
		customer.setPassword(scanner.nextLine());
		
		
		
		
		return customer;
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

		return customer;
	}
	
	public static Employee createEmployeeAccount(Scanner scanner) {
		/*
		 * Yes, you should be doing some exception and validating the user inputs.
		 */
		Employee employee = new Employee();
		System.out.println("Enter your new user account information: ");
		System.out.println("User Name: ");
		employee.setUserName(scanner.nextLine());
		System.out.println("Password: ");
		employee.setPassword(scanner.nextLine());
		System.out.println("First Name: ");
		employee.setFirstName(scanner.nextLine());
		System.out.println("Last Name: ");
		employee.setLastName(scanner.nextLine());
		System.out.println("Street Address: ");
		employee.setStreet(scanner.nextLine());
		System.out.println("City: ");
		employee.setCity(scanner.nextLine());
		System.out.println("State: ");
		employee.setState(scanner.nextLine());
		System.out.println("5 digit Zip code: ");
		employee.setZip(scanner.nextInt());

		return employee;
	}
}
