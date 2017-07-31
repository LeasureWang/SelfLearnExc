package com.jnu.thr.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestResultSet {

	/*@Test
	public void test() {
		System.out.println(testResultSet());
	}

	public String testResultSet() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Integer id = null;
		String name = null;
		String email = null;
		Date birth = null;
		String str = null;
		try {
			connection = JdbcTools.dbConnection();
			statement = connection.createStatement();
			String sql = "select ID,NAME,EMAIL,BIRTH from TESTJDBC";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				id = resultSet.getInt("ID");
				name = resultSet.getString("NAME");
				email = resultSet.getString("EMAIL");
				birth = resultSet.getDate("BIRTH");
				str = id + "  " + name + "  " + email + "  " + birth;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.release(resultSet, statement, connection);
		}
		return (id + "  " + name + "  " + email + "  " + birth);
	}
*/
	@Test
	public void testResultSet() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcTools.dbConnection();
			statement = connection.createStatement();
			//String sql = "select ID,NAME,EMAIL,BIRTH from TESTJDBC";
			String sql = "select ID,NAME,EMAIL,BIRTH from jdbctest";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("NAME");
				String email = resultSet.getString("EMAIL");
				Date birth = resultSet.getDate("BIRTH");
				System.out.println(id + "  " + name + "  " + email + "  "
						+ birth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.release(resultSet, statement, connection);
		}

	}

}
