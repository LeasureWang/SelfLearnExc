package com.jnu.thr.jdbc;

import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TestPreparedStatement {

	@Test
	public void testPreparedStatement() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = (Connection) JdbcTools.dbConnection();
			String sql = "insert into student(FolwID,Type,IDCard,ExamCard,StudentName,Location,Grade) values(?,?,?,?,?,?,?)";
			preparedStatement =(PreparedStatement) connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, 2017);
			preparedStatement.setInt(2, 2017);
			preparedStatement.setInt(3, 2017);
			preparedStatement.setInt(4, 2017);
			preparedStatement.setString(5, "2017");
			preparedStatement.setString(6, "2017");
			preparedStatement.setInt(7, 2017);
			
			preparedStatement.executeUpdate();
		
			System.out.println("update successful");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.release(null, preparedStatement, connection);
		}
	}
}
