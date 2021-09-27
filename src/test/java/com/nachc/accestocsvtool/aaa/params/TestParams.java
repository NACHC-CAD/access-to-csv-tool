package com.nachc.accestocsvtool.aaa.params;

import java.io.File;
import java.sql.Connection;

import com.nachc.accestocsvtool.util.connection.MsAccessConnectionUtil;
import com.nachc.accestocsvtool.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Test params.
 */
@Slf4j
public class TestParams {

	private static final String FILE_NAME = "/northwind.accdb";

	private static final String OUTPUT_DIR_NAME = "/output/csv/nortwind";

	/**
	 * Gets test file resource path.
	 *
	 * @return the test file resource path
	 */
	public static String getTestFileResourcePath() {
		return FILE_NAME;
	}

	/**
	 * Gets test database file.
	 *
	 * @return the test database file
	 */
	public static File getTestDatabaseFile() {
		File file = FileUtil.getFile(FILE_NAME);
		return file;
	}

	/**
	 * Gets test database.
	 *
	 * @return the test database
	 */
	public static Connection getTestDatabase() {
		File file = TestParams.getTestDatabaseFile();
		log.info("Geting connection");
		Connection conn = MsAccessConnectionUtil.getConnection(file);
		log.info("Got connection: " + conn);
		return conn;
	}

	/**
	 * Gets output file.
	 *
	 * @return the output file
	 */
	public static File getOutputFile() {
		File file = FileUtil.getFile(OUTPUT_DIR_NAME);
		return file;
	}

}
