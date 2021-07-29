package com.nachc.accestocsvtool.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	public static Connection getConnection(String url) {
		try {
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}
