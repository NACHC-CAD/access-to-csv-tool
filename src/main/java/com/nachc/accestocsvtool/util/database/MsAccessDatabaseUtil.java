package com.nachc.accestocsvtool.util.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Ms access database util.
 */
@Slf4j
public class MsAccessDatabaseUtil {

    /**
     * Gets table names.
     *
	 * @param conn Java SQL connection object specifying a Database
     * @return the table names
     */
    public static List<String> getTableNames(Connection conn) {
		try {
			return doGetTableNames(conn);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	private static List<String> doGetTableNames(Connection conn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ArrayList<String> rtn = new ArrayList<String>();
			String sqlString = "";
			sqlString += "SELECT MSysObjects.Name AS table_name \n";
			sqlString += "FROM sys.MSysObjects \n";
			sqlString += "WHERE (((Left([Name],1))<>\"~\")  \n";
			sqlString += "        AND ((Left([Name],4))<>\"MSys\")  \n";
			sqlString += "        AND ((MSysObjects.Type) In (1,4,6)) \n";
			sqlString += "        AND ((MSysObjects.Flags)=0)) \n";
			sqlString += "order by MSysObjects.Name \n";
			ps = conn.prepareStatement(sqlString);
			rs = ps.executeQuery();
			log.info("Got result set");
			while (rs.next()) {
				String tableName = rs.getString("table_name");
				rtn.add(tableName);
			}
			log.info("Got " + rtn.size() + " tables");
			return rtn;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
	}

    /**
     * Gets column names.
     *
     * @param rs the Result Set
     * @return list of column names
     */
    public static List<String> getColumnNames(ResultSet rs) {
		try {
			ArrayList<String> rtn = new ArrayList<String>();
			int cnt = rs.getMetaData().getColumnCount();
			for (int i = 1; i < cnt + 1; i++) {
				String str = rs.getMetaData().getColumnName(i);
				rtn.add(str);
			}
			return rtn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
