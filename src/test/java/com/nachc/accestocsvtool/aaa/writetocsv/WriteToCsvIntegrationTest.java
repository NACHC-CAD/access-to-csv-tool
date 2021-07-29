package com.nachc.accestocsvtool.aaa.writetocsv;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import com.nachc.accestocsvtool.aaa.params.TestParams;
import com.nachc.accestocsvtool.util.AccessToCsvUtil;
import com.nachc.accestocsvtool.util.database.MsAccessDatabaseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteToCsvIntegrationTest {

	@Test
	public void shouldWriteTablesToCsv() throws Exception {
		log.info("Starting test...");
		Connection conn = null;
		try {
			conn = TestParams.getTestDatabase();
			List<String> tableNames = MsAccessDatabaseUtil.getTableNames(conn);
			log.info("Got " + tableNames.size() + " tables");
			File outputRoot = TestParams.getOutputFile();
			log.info("Writing files to: " + outputRoot.getCanonicalPath());
			for (int i = 0; i < tableNames.size(); i++) {
				String tableName = tableNames.get(i);
				log.info("Writing CSV for " + tableName);
				File outFile = new File(outputRoot, (tableName + ".csv"));
				AccessToCsvUtil.writeToCsv(tableName, outFile, conn);
			}
			log.info("CSV Files should be in: " + outputRoot.getCanonicalPath() + "!");
			log.info("Done.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
