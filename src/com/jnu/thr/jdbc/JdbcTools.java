package com.jnu.thr.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcTools {

	
	private static DataSource dataSource=null;
	static{
		dataSource=new ComboPooledDataSource("theC3P0");
	}
	
	/*
	 * db connection
	 */
	public static Connection dbConnection() throws Exception {
		//*****below is the method which is no datasource*****
//		InputStream inputStream = JdbcTools.class.getClassLoader()
//				.getResourceAsStream("jdbc.properties");
//
//		Properties properties=new Properties();
//		properties.load(inputStream);
//		
//		String driverClass=properties.getProperty("driverClass");
//		String jbdcURL=properties.getProperty("jdbcURL");
//		String dbUserName=properties.getProperty("dbUserName");
//		String dbPassword=properties.getProperty("dbPassword");
//		
//		Class.forName(driverClass);
//		return DriverManager.getConnection(jbdcURL, dbUserName, dbPassword);
		
		return dataSource.getConnection();
	}

	/*
	 * close the resource includes ResultSet, Statement and Connection
	 */
	public static void release(ResultSet resultSet,Statement statement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * close the resource includes Statement and Connection
	 */
	public static void release(Statement statement, Connection connection) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * get the system time in the log
	 */
	public static String getSystemTime() {
		Date date = new Date();
		SimpleDateFormat sysTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		return sysTime.format(date);
	}
}
