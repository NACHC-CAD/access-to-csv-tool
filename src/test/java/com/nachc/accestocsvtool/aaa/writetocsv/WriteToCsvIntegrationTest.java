package com.nachc.accestocsvtool.aaa.writetocsv;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import com.nachc.accestocsvtool.aaa.params.TestParams;
import com.nachc.accestocsvtool.util.database.MsAccessDatabaseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteToCsvIntegrationTest {

	@Test
	public void shouldWriteTablesToCsv() throws Exception {
		Connection conn = null;
		try {
			conn = TestParams.getTestDatabase();
			List<String> tableNames = MsAccessDatabaseUtil.getTableNames(conn);
		} catch (Exception exp) {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
