package com.nachc.accestocsvtool.util.file;

import java.io.File;

/**
 * FileUtil class used for getting files based on fileName.
 */
public class FileUtil {

	/**
	 * Gets file stub using fileName and defaulting swapOutMvnTestClasses to false.
	 *
	 * @param fileName the name
	 * @return the file
	 */
	public static File getFile(String fileName) {
		return getFile(fileName, false);
	}

	/**
	 * Gets file specifying both fileName and swapOutMvnTestClasses.
	 *
	 * @param fileName                  String, file name
	 * @param swapOutMvnTestClasses Boolean, if true, use 'classes' in path instead of 'test-classes'
	 * @return the file
	 */
	public static File getFile(String fileName, boolean swapOutMvnTestClasses) {
		String filePath = "/";
		String rootDirName = FileUtil.class.getResource(filePath).getPath();
		if (swapOutMvnTestClasses) {
			rootDirName = rootDirName.replace("test-classes", "classes");
		}
		File rtn = new File(rootDirName, fileName);
		if (rtn.exists()) {
			rootDirName = rootDirName.replace("test-classes", "classes");
			File temp = new File(rootDirName, fileName);

			// Only move to classes if file exists there, otherwise, stay in test-classes
			if (temp.exists()) {
				rtn = temp;
			}
		}
		return rtn;
	}

}
