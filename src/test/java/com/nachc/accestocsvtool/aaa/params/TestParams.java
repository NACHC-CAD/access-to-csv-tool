package com.nachc.accestocsvtool.aaa.params;

import java.io.File;

import com.nachc.accestocsvtool.util.file.FileUtil;

public class TestParams {

	private static final String FILE_NAME = "/northwind.accdb";

	public static String getTestFileResourcePath() {
		return FILE_NAME;
	}

	public static File getTestDatabaseFile() {
		File file = FileUtil.getFile(FILE_NAME);
		return file;
	}

}
