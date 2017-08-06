package com.jnu.junit.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.jnu.thr.jdbc.JdbcTools;

public class TestNewConnectionInC3P0 {

	@Test
	public void test() throws Exception {
		Connection connection=JdbcTools.dbConnection();
		System.out.println(connection);
	}

}
