package com.nachc.accestocsvtool.util.connection;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Test;

import com.nachc.accestocsvtool.aaa.params.TestParams;
import com.nachc.accestocsvtool.util.database.DatabaseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionUtilIntegrationTest {

	@Test
	public void shouldGetConnection() throws Exception {
		Connection conn = null;
		try {
			log.info("Starting test...");
			File file = TestParams.getTestDatabaseFile();
			log.info("Geting connection");
			conn = ConnectionUtil.getConnection(file);
			log.info("Got connection: " + conn);
			assertTrue(conn != null);
			showTables(conn);
			log.info("Done.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private void showTables(Connection conn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sqlString = "SELECT * FROM sys.MSysObjects WHERE Type=1 AND Flags=0";
			ps = conn.prepareStatement(sqlString);
			rs = ps.executeQuery();
			log.info("Got result set");
			List<String> colNames = DatabaseUtil.getColumnNames(rs);
			log.info("Got columnNames");
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
		}
	}

}
