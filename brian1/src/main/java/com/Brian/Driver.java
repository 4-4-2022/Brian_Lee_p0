package com.Brian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.Brian.client.AppUI;
import com.Brian.model.Customer;
import com.Brian.model.Hotdog;
import com.Brian.model.HotdogTemplate;
import com.Brian.model.User;
import com.Brian.respository.hotdogRespositoryImp;


public class Driver {
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean isUserInterested = true;
		hotdogRespositoryImp newRepo = new hotdogRespositoryImp();
		ArrayList<Hotdog> hotdogs = newRepo.findAllHotdogs();
		

		
		while (isUserInterested) {
			AppUI.printWelcomeMenu();
			int userSelection =AppUI.handleUserSelection(scanner); //handle input mismatch for zip(int)

			switch (userSelection) {
			case 1:
				Customer customer = (Customer) AppUI.loginUser(scanner);
				if( customer != null) {
					AppUI.mainMenu(scanner, customer);
				}
				else {
					System.out.println("Incorrect User Information");
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
}
