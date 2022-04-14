package com.Brian.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {
		
		/*
		 * We always need a connection to a DB before we can access any of the records. Let's first
		 * start by getting a connection.
		 * 
		 * In order to establish this connection, we need to use JDBC (Java Database Connectivity).
		 * Understand that JDBC provides an interface against which all libraries specific to any
		 * database management system must program (e.g. they are required to provide implementations
		 * of those interfaces).
		 * 
		 * That said, JDBC has several interfaces and classes that are considered standard:
		 * 
		 * Connection (Interface): represents a connection to a database.
		 * DriverManager (Class): a class that is used to use any JDBC drivers in order to establish
		 * a connection to a database or even set additional configuration such as login timeouts.
		 * SQLException (Class): It's just an exception that is thrown when something goes wrong when
		 * you're working with JDBC.
		 */
		Connection conn = null;
		try {
		conn = DriverManager.getConnection(
				System.getenv("db_url"), 
				System.getenv("db_username"), 
				System.getenv("db_password")
			);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceCloser.closeConnection(conn);
		}
		
		return conn;
	}
}