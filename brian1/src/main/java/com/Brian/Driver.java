package com.Brian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.Brian.model.HotdogTemplate;

public class Driver {
	
	
	public static void main(String[] args) {
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
		
		
		
		HotdogTemplate hotDogMenu = new HotdogTemplate();
		System.out.println(hotDogMenu.toString());
	}
}
