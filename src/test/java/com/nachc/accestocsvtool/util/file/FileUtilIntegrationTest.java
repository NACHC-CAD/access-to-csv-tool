package com.nachc.accestocsvtool.util.file;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.nachc.accestocsvtool.aaa.params.TestParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtilIntegrationTest {
	
	@Test
	public void shouldGetFile() {
		try {
			log.info("Starting test...");
			String resourcePath = TestParams.getTestFileResourcePath();
			log.info("Got file path: " + resourcePath);
			File file = FileUtil.getFile(resourcePath);
			String filePath = file.getCanonicalPath();
			log.info("Got file path: " + filePath);
			boolean fileExists = file.exists();
			log.info("File exists: " + fileExists);
			assertTrue(fileExists == true);
			log.info("Done.");
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}
