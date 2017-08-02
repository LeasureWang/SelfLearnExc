package com.jnu.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jnu.thr.jdbc.Customer;
import com.jnu.thr.jdbc.DAO;

public class DAOTest {

	DAO dao = new DAO();

	@Test
	public void testUpdate() {

		String sql = "insert into tb_customer(cutr_key,cutr_name,cutr_location,cutr_tel)"
				+ "values(?,?,?,?)";
		dao.update(sql, 2017, "kk", "guangzhou", 211);

	}

	@Test
	public void testGet() {
		String sql = "select cutr_key customerKey,cutr_name customerName,"
				+ "cutr_location customerLocation,cutr_tel customerTel from tb_customer where cutr_key=?";
		Customer customer = dao.get(Customer.class, sql, 2017);
		System.out.println(customer);
	}

	@Test
	public void testGetForList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForValue() {
		fail("Not yet implemented");
	}

}
