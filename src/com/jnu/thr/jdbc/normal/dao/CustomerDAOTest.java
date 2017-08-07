package com.jnu.thr.jdbc.normal.dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.jnu.thr.jdbc.Customer;
import com.jnu.thr.jdbc.JdbcTools;

public class CustomerDAOTest {

	CustomerDAO customerDao=new CustomerDAO();
	
	@Test
	public void testBatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		Connection connection=null;
		try{
			connection=JdbcTools.dbConnection();
			String sql = "select cutr_key customerKey,cutr_name customerName,"
					+ "cutr_location customerLocation,cutr_tel customerTel from tb_customer where cutr_key=?";
			Customer customer=customerDao.get(connection, sql, 20);
			System.out.println(customer);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcTools.release(null, connection);
		}
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
