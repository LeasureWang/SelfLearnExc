package com.jnu.thr.jdbc.exc;

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

import org.junit.Test;

import com.jnu.thr.jdbc.JdbcTools;
import com.mysql.jdbc.PreparedStatement;

public class JdbcToolsExcoo1 {

	/*
	 * use preparedStatement to do insert,update,delete
	 */
	public static void update2(String sql, Object ... args) {

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try{
			connection=JdbcTools.dbConnection();
			preparedStatement=(PreparedStatement) connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				preparedStatement.setObject(i+1,args[i]);
			}
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcTools.release(null, preparedStatement, connection);
		}
	}

	/*
	 * use Statement to do insert,update,delete
	 */
	public static void update(String sql) throws Exception {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = JdbcTools.dbConnection();
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.release(statement, connection);
		}
		System.out.println("[INFO] " + JdbcTools.getSystemTime()
				+ " |update successfully|");
	}

	/*
	 * db connection
	 */
	public static Connection dbConnection() throws Exception {
		InputStream inputStream = JdbcToolsExcoo1.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");

		Properties properties = new Properties();
		properties.load(inputStream);

		String driverClass = properties.getProperty("driverClass");
		String jbdcURL = properties.getProperty("jdbcURL");
		String dbUserName = properties.getProperty("dbUserName");
		String dbPassword = properties.getProperty("dbPassword");

		Class.forName(driverClass);
		return DriverManager.getConnection(jbdcURL, dbUserName, dbPassword);
	}

	/*
	 * close the resource includes ResultSet, Statement and Connection
	 */
	public static void release(ResultSet resultSet, Statement statement,
			Connection connection) {
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
