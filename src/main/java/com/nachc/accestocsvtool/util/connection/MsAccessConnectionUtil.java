package com.nachc.accestocsvtool.util.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class MsAccessConnectionUtil {

	public static Connection getConnection(File file) {
		String url = DatabaseUrlFactory.getJdbcConnectionString(file);
		Connection conn = getConnection(url);
		return conn;
	}
	
	public static Connection getConnection(String url) {
		try {
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}
