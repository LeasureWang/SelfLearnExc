package com.jnu.thr.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

public class TestStatement {

	@Test
	public void test() throws Exception {
		 //String sql ="insert into jdbctest(ID,NAME,EMAIL,BIRTH) values(1001022222,'yd2','qwe@100616.com','1998-01-02')";
		String sql = "delete from jdbctest where ID in(10086165,10010)";
		// String sql ="update jdbctest set NAME='hello10086' where ID=1008616";
		update(sql);
	}

	/*
	 * use Statement to do insert,update,delete
	 */
	public void update(String sql) throws Exception {
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
		System.out.println("[INFO] " + JdbcTools.getSystemTime()+ " |update successfully|");
	}
	
}
