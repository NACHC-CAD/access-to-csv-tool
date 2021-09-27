package com.nachc.accestocsvtool.util.connection;

import java.io.File;

/**
 * The type Database url factory.
 */
public class DatabaseUrlFactory {

	/**
	 * Gets jdbc connection string.
	 *
	 * @param file the file
	 * @return the jdbc connection string
	 */
	public static String getJdbcConnectionString(File file) {
		try {
			String url;
			String fileName = file.getCanonicalPath();

			fileName = fileName.replace("\\", "//");

			url = "jdbc:ucanaccess://" + fileName;
			url = url + ";sysSchema=true";

			return url;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
