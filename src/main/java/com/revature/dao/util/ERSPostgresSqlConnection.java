package com.revature.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ERSPostgresSqlConnection {
	
private static Connection connection;
	
	private ERSPostgresSqlConnection() {
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(ERSDbUtilProps.DRIVER);
		String url = ERSDbUtilProps.URL;
		String username = ERSDbUtilProps.USERNAME;
		String password = ERSDbUtilProps.PASSWORD;
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

}
