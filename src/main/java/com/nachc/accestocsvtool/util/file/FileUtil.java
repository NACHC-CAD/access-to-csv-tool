package com.nachc.accestocsvtool.util.file;

import org.apache.commons.lang.ObjectUtils;

import java.io.File;

public class FileUtil {

	public static File getFile(String name) {
		return getFile(name, false);
	}

	public static File getFile(String name, boolean swapOutMvnTestClasses) {
		String filePath = "/";
		String rootDirName = FileUtil.class.getResource(filePath).getPath();

		if (swapOutMvnTestClasses) {
			rootDirName = rootDirName.replace("test-classes", "classes");
		}

		File rtn = new File(rootDirName, name);

		if (!rtn.exists()) {
			File temp = new File(rootDirName, name);

			// Only move to classes if file exists there, otherwise, stay in test-classes
			if (temp.exists()) {
				rtn = temp;
			}
		}

		return rtn;
	}

}
