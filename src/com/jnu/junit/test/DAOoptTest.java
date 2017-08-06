package com.jnu.junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.jnu.thr.jdbc.Customer;
import com.jnu.thr.jdbc.DAO;
import com.jnu.thr.jdbc.DAOopt;

public class DAOoptTest {

	DAOopt daoOpt = new DAOopt();

	@Test
	public void testUpdate() {

		String sql = "insert into tb_customer(cutr_key,cutr_name,cutr_location,cutr_tel)"
				+ "values(?,?,?,?)";
		daoOpt.update(sql, 20, "jnu", "tianhe", 985211);

	}

	@Test
	public void testGet() {
		String sql = "select cutr_key customerKey,cutr_name customerName,"
				+ "cutr_location customerLocation,cutr_tel customerTel from tb_customer where cutr_key=?";
		Customer customer = daoOpt.get(Customer.class, sql, 20);
		System.out.println(customer);
	}

	@Test
	public void testGetForList() {
		String sql = "select cutr_key customerKey,cutr_name customerName,"
				+ "cutr_location customerLocation,cutr_tel customerTel from tb_customer";
		List<Customer> customer = daoOpt.getForList(Customer.class, sql);
	
		System.out.println(customer);

	}

}
