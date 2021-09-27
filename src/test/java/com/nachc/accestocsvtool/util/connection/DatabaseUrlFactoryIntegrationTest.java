package com.nachc.accestocsvtool.util.connection;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.nachc.accestocsvtool.aaa.params.TestParams;
import com.nachc.accestocsvtool.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Database url factory integration test.
 */
@Slf4j
public class DatabaseUrlFactoryIntegrationTest {

    /**
     * Should get url.
     */
    @Test
	public void shouldGetUrl() {
		String testFileName = TestParams.getTestFileResourcePath();
		File file = FileUtil.getFile(testFileName);
		boolean fileExists = file.exists();
		log.info("File exists: " + fileExists);
		assertTrue(fileExists == true);
		String url = DatabaseUrlFactory.getJdbcConnectionString(file);
		log.info("Got url: " + url);
		log.info("Done.");
	}
	
}
