package com.nachc.accestocsvtool.util.connection;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.nachc.accestocsvtool.util.file.FileUtil;
import org.junit.Test;

import com.nachc.accestocsvtool.util.database.MsAccessDatabaseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsAccessConnectionUtilIntegrationTest {

	private static final String FILE_NAME = "/northwind.accdb";

	@Test
	public void TestGetConnectionFromFile() throws Exception {

		//Arrange
		File file = FileUtil.getFile(FILE_NAME);

		//Act
		try (Connection conn = MsAccessConnectionUtil.getConnection(file)) {

			//Assert
			assertNotNull(conn);
		}
	}
}
