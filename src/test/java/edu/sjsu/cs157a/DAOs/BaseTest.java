package edu.sjsu.cs157a.DAOs;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

public class BaseTest {

	private static Connection conn;
	private static Properties prop;
	
	public static void initConnectionAndDatabase() {
		try {
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/hibernate.properties"));
			conn = DriverManager.getConnection(prop.getProperty("hibernate.connection.url"),
											   prop.getProperty("hibernate.connection.username"),
											   prop.getProperty("hibernate.connection.password"));
			executeSQLScript(conn, "reservation.sql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void executeSQLScript(Connection conn, String fileName) throws Exception {
		final String basePath = new File("").getAbsolutePath();
		final String projectPath = prop.getProperty("projectPath");
		final String aSQLScriptFilePath = basePath + projectPath + fileName;
		ScriptRunner sr = null;

		try {
			sr = new ScriptRunner(conn);
			Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));
			sr.runScript(reader);
		} catch (Exception e) {
			throw e;
		}
	}
}
