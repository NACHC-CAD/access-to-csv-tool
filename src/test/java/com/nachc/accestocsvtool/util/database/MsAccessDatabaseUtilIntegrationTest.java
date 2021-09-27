package com.nachc.accestocsvtool.util.database;

import com.nachc.accestocsvtool.util.connection.MsAccessConnectionUtil;
import com.nachc.accestocsvtool.util.database.MsAccessDatabaseUtil;
import com.nachc.accestocsvtool.util.file.FileUtil;

import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.assertNotNull;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsAccessDatabaseUtilIntegrationTest {

    private static final String FILE_NAME = "/northwind.accdb";

    @Test
    public void TestGetTableNames() throws Exception {

        //Arrange
        File file = FileUtil.getFile(FILE_NAME);
        try (Connection conn = MsAccessConnectionUtil.getConnection(file)) {

            //Act
            List<String> tableNames = MsAccessDatabaseUtil.getTableNames(conn);

            //Assert
            assertNotNull(tableNames);
        }
    }

    @Test
    public void TestGetColumnNames() throws Exception {

        //Arrange
        File file = FileUtil.getFile(FILE_NAME);
        try (Connection conn = MsAccessConnectionUtil.getConnection(file)) {

            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM Privileges");

            //Act
            List<String> columnNames = MsAccessDatabaseUtil.getColumnNames(result);

            //Assert
            assertNotNull(columnNames);
        }
    }
}
