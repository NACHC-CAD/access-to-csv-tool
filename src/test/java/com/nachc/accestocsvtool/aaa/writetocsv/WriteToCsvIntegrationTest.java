package com.nachc.accestocsvtool.aaa.writetocsv;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.List;

import com.nachc.accestocsvtool.util.file.FileUtil;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.nachc.accestocsvtool.util.AccessToCsvUtil;
import com.nachc.accestocsvtool.util.database.MsAccessDatabaseUtil;
import com.nachc.accestocsvtool.util.connection.MsAccessConnectionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteToCsvIntegrationTest {

	private static final String FILE_NAME = "/northwind.accdb";
	private static final String OUTPUT_DIR_NAME = "/output/csv/nortwind";

	@Test
	public void TestWriteToCsv() throws Exception {

		//Arrange
		File file = FileUtil.getFile(FILE_NAME);

		try (Connection conn = MsAccessConnectionUtil.getConnection(file)) {

			List<String> tableNames = MsAccessDatabaseUtil.getTableNames(conn);
			File outputRoot = FileUtil.getFile(OUTPUT_DIR_NAME);

			for (int i = 0; i < tableNames.size(); i++) {
				String tableName = tableNames.get(i);
				File outFile = new File(outputRoot, (tableName + ".csv"));

				//Act
				AccessToCsvUtil.writeToCsv(tableName, outFile, conn);
			}
		}
	}

	@Test
	public void TestWriteQueryResultsToCsv() throws Exception {

		//Arrange
		File file = FileUtil.getFile(FILE_NAME);

		try (Connection conn = MsAccessConnectionUtil.getConnection(file)) {
			List<String> tableNames = MsAccessDatabaseUtil.getTableNames(conn);

			File outputRoot = FileUtil.getFile(OUTPUT_DIR_NAME);
			for (int i = 0; i < tableNames.size(); i++) {
				String tableName = tableNames.get(i);
				String sqlString = "select * from [" + tableName + "]";
				File outFile = new File(outputRoot, (tableName + ".csv"));

				assertTrue(outFile.exists());
				//Act
				AccessToCsvUtil.writeQueryResultsToCsv(sqlString, outFile, conn);
			}
		}
	}
}
