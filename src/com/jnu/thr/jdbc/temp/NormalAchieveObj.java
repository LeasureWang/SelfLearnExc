package com.jnu.thr.jdbc.temp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jnu.thr.jdbc.Customer;
import com.jnu.thr.jdbc.JdbcTools;
import com.jnu.thr.jdbc.ReflectionUtils;

public class NormalAchieveObj {

	@Test
	public void test() {
		String sql = "select cutr_key customerKey,cutr_name customerName,cutr_location customerLocation,cutr_tel customerTel from tb_customer where cutr_key=?";
		Customer customer = get(Customer.class, sql, 1008616);
		System.out.println(customer);
	}
	
	
	
	/*
	 *according to the sql and Class Object can achieve the object of the result 
	 */
	public <T> T get(Class<T> clazz, String sql, Object... args) {

		T entity = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// achieve the ResultSet
			connection = JdbcTools.dbConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();

			// achieve the ResultSetMetaData Object
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			// create Map<String,Object>
			Map<String, Object> values = new HashMap<String, Object>();

			// use the resultSetMetaData to fill the Map Object
			if (resultSet.next()) {
				for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
					String columnLabel = resultSetMetaData
							.getColumnLabel(i + 1);
					Object columnValue = resultSet.getObject(i + 1);
					values.put(columnLabel, columnValue);
				}
			}
			// create clazz with reflection when Map is not null
			if (values.size() > 0) {
				entity = clazz.newInstance();
				// set the field
				for (Map.Entry<String, Object> entry : values.entrySet()) {
					String fieldName = entry.getKey();
					Object fielsValue = entry.getValue();
					ReflectionUtils
							.setFieldValue(entity, fieldName, fielsValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.release(resultSet, preparedStatement, connection);
		}
		return entity;

	}
}
