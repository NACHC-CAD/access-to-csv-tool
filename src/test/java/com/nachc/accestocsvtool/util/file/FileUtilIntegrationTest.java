package com.nachc.accestocsvtool.util.file;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtilIntegrationTest {

	private static final String FILE_NAME = "/northwind.accdb";

	@Test
	public void TestGetFileDefault() {

		//Arrange
		File file;

		//Act
		file = FileUtil.getFile(FILE_NAME);

		//Assert
		assertTrue(file.exists());
	}

	@Test
	public void TestGetFileWithBoolFalse() {

		//Arrange
		File file;

		//Act
		file = FileUtil.getFile(FILE_NAME, false);

		//Assert
		assertTrue(file.exists());
	}
}
