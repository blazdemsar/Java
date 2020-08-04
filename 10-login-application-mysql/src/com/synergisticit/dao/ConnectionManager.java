package com.synergisticit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private Connection connection;
	public ConnectionManager(String driver, String url, String username, String password) 
			throws ClassNotFoundException,SQLException{
		
		Class.forName(driver);
		this.connection = DriverManager.getConnection(url, username, password);
	}
	public Connection getConnection() {
		return this.connection;
	}

}
