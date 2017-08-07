package com.jnu.thr.jdbc.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

public class TheDBCP {

	/**
	 * normal method to create dbcp
	 * @throws Exception
	 */
	@Test
	public void testDBCP() throws Exception {
		/*
		 * BasicDataSource dataSource = new BasicDataSource(); //set the filed
		 * dataSource.setUsername("root"); dataSource.setPassword("tiger");
		 * dataSource.setUrl("jdbc:mysql://localhost:3306/jdbctest");
		 * dataSource.setDriverClassName("com.mysql.jdbc.Driver"); //connect
		 * Connection connection = dataSource.getConnection();
		 * System.out.println(connection);
		 */
	}

	/**
	 * factory method to creat dbcp
	 * @throws Exception
	 */
	@Test
	public void testDBCPDataSourceFactory() throws Exception {
		Properties properties = new Properties();
		InputStream inputStream = TheDBCP.class.getClassLoader()
				.getSystemResourceAsStream("dbcp.properties");
		properties.load(inputStream);
		
		DataSource dataSource=BasicDataSourceFactory.createDataSource(properties);
		System.out.println(dataSource.getConnection());
	}

}
