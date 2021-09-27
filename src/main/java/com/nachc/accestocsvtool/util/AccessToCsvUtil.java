package com.nachc.accestocsvtool.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.nachc.accestocsvtool.util.connection.MsAccessConnectionUtil;
import com.nachc.accestocsvtool.util.database.MsAccessDatabaseUtil;

/**
 * AccessToCsvUtil type providing utilities specific to CSV filetype.
 */
public class AccessToCsvUtil {

	/**
	 * Gets connection.
	 *
	 * @param file The file
	 * @return A Java SQL connection object specifying a Database
	 */
	public static Connection getConnection(File file) {
		return MsAccessConnectionUtil.getConnection(file);
	}

	/**
	 * Gets table names.
	 *
	 * @param conn Java SQL connection object specifying a Database
	 * @return the table names
	 */
	public static List<String> getTableNames(Connection conn) {
		return MsAccessDatabaseUtil.getTableNames(conn);
	}

	/**
	 * Write to csv.
	 *
	 * @param tableName the table name
	 * @param outFile   the output file
	 * @param conn      Java SQL connection object specifying a Database
	 */
	public static void writeToCsv(String tableName, File outFile, Connection conn) {
		try {
			String sqlString = "select * from [" + tableName + "]";
			doWriteToCsv(sqlString, outFile, conn);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	/**
	 * Write query results to csv.
	 *
	 * @param sqlString the sql string
	 * @param outFile   the out file
	 * @param conn      Java SQL connection object specifying a Database
	 */
	public static void writeQueryResultsToCsv(String sqlString, File outFile, Connection conn) {
		try {
			doWriteToCsv(sqlString, outFile, conn);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	private static void doWriteToCsv(String sqlString, File outFile, Connection conn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sqlString);
			rs = ps.executeQuery();
			File dir = outFile.getParentFile();
			if(dir != null) {
				dir.mkdirs();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(rs));
			csvPrinter.printRecords(rs);
			csvPrinter.close();
		} catch(Throwable exp) {
			throw new RuntimeException(exp);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
	}

}
