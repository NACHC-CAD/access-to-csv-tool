package com.nachc.accestocsvtool.util.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.nachc.accestocsvtool.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseUrlFactoryIntegrationTest {

	private static final String FILE_NAME = "/northwind.accdb";

	@Test
	public void TestGetJdbcConnectionStringFromFile() {

		//Arrange
		File file = FileUtil.getFile(FILE_NAME);

		//Act
		String url = DatabaseUrlFactory.getJdbcConnectionString(file);

		//Assert
		assertEquals(url,"jdbc:ucanaccess://" + file.getAbsolutePath().replace("\\", "//") + ";sysSchema=true");
	}
}
