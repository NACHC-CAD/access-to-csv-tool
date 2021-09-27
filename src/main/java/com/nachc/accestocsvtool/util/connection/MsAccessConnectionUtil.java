package com.nachc.accestocsvtool.util.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The type Ms access connection util.
 */
public class MsAccessConnectionUtil {

	/**
	 * Gets connection using a JDBC Connection String.
	 *
	 * @param file the file
	 * @return the connection
	 */
	public static Connection getConnection(File file) {
		String url = DatabaseUrlFactory.getJdbcConnectionString(file);
		return getConnection(url);
	}

	/**
	 * Gets connection using a URL.
	 *
	 * @param url the url to connect to
	 * @return the connection
	 */
	public static Connection getConnection(String url) {
		try {
			return DriverManager.getConnection(url);
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}
