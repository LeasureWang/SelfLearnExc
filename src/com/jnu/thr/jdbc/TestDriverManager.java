package com.jnu.thr.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class TestDriverManager {

	/*
	 * use Driver to get the DB connection
	 */
	@Test
	public void testDC() throws Exception {
		System.out.println(testDriverConnection());
	}

	public Connection testDriverConnection() throws Exception {

		// get the information in the properties
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		// get the four String key to connect
		String driverClass = properties.getProperty("driverClass");
		String jdbcURL = properties.getProperty("jdbcURL");
		String dbUserName = properties.getProperty("dbUserName");
		String dbPassword = properties.getProperty("dbPassword");

		// use reflect to get the Driver object
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Properties info = new Properties();
		info.put("user", dbUserName);
		info.put("password", dbPassword);

		// get the successful connection with connect method of driver
		Connection connection = driver.connect(jdbcURL, info);
		return connection;
	}

	/*
	 * use DriverManager to get the DB connection
	 */
	@Test
	public void testDMC() throws Exception {
		System.out.println(testDriverManagerConnection());
	}

	public Connection testDriverManagerConnection() throws Exception {

		// get the information in the properties
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(inputStream);

		// get the four String key to connect
		String driverClass = properties.getProperty("driverClass");
		String jdbcURL = properties.getProperty("jdbcURL");
		String dbUserName = properties.getProperty("dbUserName");
		String dbPassword = properties.getProperty("dbPassword");

		// needn't new instance, because of the register has been done in static
		// field
		Class.forName(driverClass);
		// get the successful connection with DriverManager
		return DriverManager.getConnection(jdbcURL, dbUserName, dbPassword);
	}

}
