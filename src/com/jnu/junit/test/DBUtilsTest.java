package com.jnu.junit.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.Test;

import com.jnu.thr.jdbc.JdbcTools;

public class DBUtilsTest {

	QueryRunner queryRunner = new QueryRunner();

	class TheResultSetHandler implements ResultSetHandler {

		@Override
		public Object handle(ResultSet arg0) throws SQLException {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			return "at you!";
		}

	}

	/**
	 * query/select
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testQuery() {
		Connection connection = null;
		try {
			connection = JdbcTools.dbConnection();
			String sql = "select cutr_key customerKey,cutr_name customerName,"
					+ "cutr_location customerLocation,cutr_tel customerTel from tb_customer";

			Object obj = queryRunner.query(connection, sql,
					new TheResultSetHandler());
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.release(null, connection);
		}
	}

	/**
	 * delete, update, insert
	 */
	@Test
	public void testUpdate() {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "delete from tb_customer where cutr_key in(?,?)";
		Connection connection = null;
		try {
			connection = JdbcTools.dbConnection();
			queryRunner.update(connection, sql, 2017, 10086001);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.release(null, connection);
		}

	}

}
