package com.nachc.accestocsvtool.util.connection;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import com.nachc.accestocsvtool.aaa.params.TestParams;
import com.nachc.accestocsvtool.util.database.MsAccessDatabaseUtil;

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
			log.info("Done.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Test
	public void shouldGetTables() throws Exception {
		log.info("Starting test...");
		Connection conn = null;
		try {
			File file = TestParams.getTestDatabaseFile();
			conn = ConnectionUtil.getConnection(file);
			log.info("Got connection");
			List<String> tableNames = MsAccessDatabaseUtil.getTableNames(conn);
			log.info("Got " + tableNames.size() + " tables");
			for (int i = 0; i < tableNames.size(); i++) {
				log.info("\t" + tableNames.get(i));
			}
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
