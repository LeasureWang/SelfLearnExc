package com.jnu.thr.jdbc.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.jnu.thr.jdbc.JdbcTools;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TheC3P0 {

	@Test
	public void testC3P0() throws SQLException{
		
		DataSource dataSource=new ComboPooledDataSource("theC3P0");
		System.out.println(dataSource.getConnection());

//		ComboPooledDataSource combopooledDataSource=(ComboPooledDataSource) dataSource;
//		System.out.println(combopooledDataSource.getMaxStatements());
		
	}
}
