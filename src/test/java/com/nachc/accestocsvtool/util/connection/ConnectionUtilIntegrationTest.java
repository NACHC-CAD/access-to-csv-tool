package com.nachc.accestocsvtool.util.connection;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import com.nachc.accestocsvtool.aaa.params.TestParams;
import com.nachc.accestocsvtool.util.database.MsAccessDatabaseUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Connection util integration test.
 */
@Slf4j
public class ConnectionUtilIntegrationTest {

	/**
	 * Should get connection.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldGetConnection() throws Exception {
		Connection conn = null;
		try {
			log.info("Starting test...");
			File file = TestParams.getTestDatabaseFile();
			log.info("Geting connection");
			conn = MsAccessConnectionUtil.getConnection(file);
			log.info("Got connection: " + conn);
			assertTrue(conn != null);
			log.info("Done.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * Should get tables.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldGetTables() throws Exception {
		log.info("Starting test...");
		Connection conn = null;
		try {
			File file = TestParams.getTestDatabaseFile();
			conn = MsAccessConnectionUtil.getConnection(file);
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
