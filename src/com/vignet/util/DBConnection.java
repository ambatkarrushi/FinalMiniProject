package com.vignet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	// create static reference variable
	private static DBConnection instance;

	private final String URL = "jdbc:mysql://localhost:4141/car_sales_system";
	private final String USERNAME = "root";
	private final String PASSWORD = "root";

	// create private constructor
	private DBConnection() {
		try {
			// load class driver
			Class.forName("com.mysql.jdbc.Driver");
		} // try
		catch (Exception e) {
			e.printStackTrace();
		} // catch
	}// constructor

	// get connection
	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new DBConnection();
		} // if

		try {
			return DriverManager.getConnection(instance.URL, instance.USERNAME, instance.PASSWORD);
		} // try
		catch (SQLException e) {
			throw e;
		} // catch
	}// getConnection method
	
	// close connection
	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			} // if
		} // try
		catch (SQLException e) {
			e.printStackTrace();
		} // catch
	}// close connection
}// DBConnection class