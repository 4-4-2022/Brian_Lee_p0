package com.Brian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.Brian.client.AppUI;
import com.Brian.model.HotdogTemplate;


public class Driver {
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean isUserInterested = true;
//		try {
//			Connection conn = DriverManager.getConnection
//					(System.getenv("db_url_p0"), 
//					System.getenv("db_username_p0"), 
//					System.getenv("db_password_p0"));
//		}catch(SQLException e){
//			e.printStackTrace();
//		}finally {
//			
//		}
		
		while (isUserInterested) {
			AppUI.printWelcomeMenu();
			int userSelection =AppUI.handleUserSelection(scanner); //handle input mismatch for zip(int)

			switch (userSelection) {
			case 1:
				AppUI.loginCustomer(scanner);
				break;
			case 2:
				AppUI.loginEmployee(scanner);
				break;
			case 3:
				AppUI.createCustomerAccount(scanner);
				break;
			case 4:
				AppUI.createEmployeeAccount(scanner);
				break;
			case 5:
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
