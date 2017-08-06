package com.jnu.junit.test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.jnu.thr.jdbc.Customer;

public class BeanUtilsTest {

	
	
	@Test
	public void getProperty() throws Exception{
		Object obj=new Customer();
		System.out.println(obj);
		
		BeanUtils.setProperty(obj, "customerName", "--SET_NAME");
		System.out.println(obj);
		
		BeanUtils.getProperty(obj, "customerName");
		System.out.println("-->"+BeanUtils.getProperty(obj, "customerName"));
	}
	@Test
	public void testSetProperty() throws Exception {
		Object obj=new Customer();
		System.out.println(obj);
		
		BeanUtils.setProperty(obj, "customerName", "--GET_NAME");
		System.out.println(obj);
		
		
		}

}
