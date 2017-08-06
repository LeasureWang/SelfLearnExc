package com.jnu.junit.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.jnu.thr.jdbc.DAO;
import com.jnu.thr.jdbc.JdbcTools;

public class transactionTest {

	@Test
	public void nonTransactionTest() {
		DAO dao = new DAO();
		String sql = "UPDATE tb_accountbalance SET balance=balance-1000 WHERE account='cicky'";
		dao.update(sql);
		// need the transaction because of it will no have the correct balance
		// when in some error
		int i = 2 / 0;
		sql = "UPDATE tb_accountbalance SET balance=balance+1000 WHERE account='ele'";
		dao.update(sql);
	}

	@Test
	public void transactionTest() throws Exception {

		String sql = "";
		Connection connection = null;
		try {

			connection = JdbcTools.dbConnection();

			// cancel the autoCommit
			connection.setAutoCommit(false);

			sql = "UPDATE tb_accountbalance SET balance=balance-1000 WHERE account='cicky'";
			update(connection, sql);
			int i = 2 / 0;
			sql = "UPDATE tb_accountbalance SET balance=balance+1000 WHERE account='ele'";
			update(connection, sql);

			// commit
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.release(null, connection);
		}
	}

	public void update(Connection connection, String sql, Object... args) {

		PreparedStatement preparedStatement = null;
		try {
			// connection = JdbcTools.dbConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			preparedStatement.executeUpdate();
			System.out.println("	|successfully!!|	");
		} catch (Exception e) {
			e.printStackTrace();

			// rollback
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			JdbcTools.release(null, preparedStatement, null);
		}
	}
}
