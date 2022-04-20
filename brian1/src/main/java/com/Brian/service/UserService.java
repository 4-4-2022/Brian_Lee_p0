package com.Brian.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.Brian.model.User;

public class UserService {
	
	public ArrayList<User> findAllUsers() {
		return User.findAll();
	}
	
	public static String trimString(String userInput) {
		String reducedString = userInput.trim();
//		Scanner scanner = new Scanner(System.in);
//		Boolean isInvalid = true;
//		while(isInvalid = true) {
//			if(reducedString.length() == 0) {
//				System.out.println("Empty entry, please re-enter information.");
//				trimString(scanner.next());
//			}
//			else {
//				isInvalid = false;
//			}
//			
//		}

		return reducedString;
	}
}
