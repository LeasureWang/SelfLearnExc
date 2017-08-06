package com.jnu.thr.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class DAOopt {

	// insert, update, delete
	public void update(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcTools.dbConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			preparedStatement.executeUpdate();
			System.out.println("	|successfully!!|	");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.release(preparedStatement, connection);
		}
	}

	// return the Object of one record
	public <T> T get(Class<T> clazz, String sql, Object... args) {

		List<T> result=getForList(clazz,sql,args);
		if(result.size()>0){
			return result.get(0);
		}
		return null;
		
	}

	// return the list of the Object what have many records
	public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {

		List<T> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = JdbcTools.dbConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();

			List<Map<String, Object>> values = handleResultSetToMapList(resultSet);

			list=transferMapListToBeanList(clazz, values);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.release(resultSet, preparedStatement, connection);
		}

		// return the result
		return list;
	}

	private <T> List<T> transferMapListToBeanList(Class<T> clazz,
			List<Map<String, Object>> values) throws InstantiationException,
			IllegalAccessException, InvocationTargetException {

		List<T> result = new ArrayList<T>();

		T bean = null;
		if (values.size() > 0) {
			for (Map<String, Object> m : values) {
				bean = clazz.newInstance();
				for (Map.Entry<String, Object> entry : m.entrySet()) {
					String propertyName = entry.getKey();
					Object value = entry.getValue();
					BeanUtils.setProperty(bean, propertyName, value);
				}
				// add to the list
				result.add(bean);
			}
		}
		return result;
	}

	/**
	 * 
	 * one Map one list according to the resultSet
	 * 
	 * @param resultSet
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	private List<Map<String, Object>> handleResultSetToMapList(
			ResultSet resultSet) throws Exception, SQLException {
		// prepare a List<Map<String,Object>> for record, one Map one record
		List<Map<String, Object>> values = new ArrayList<>();

		List<String> columnLabels = getColumnLabels(resultSet);

		Map<String, Object> map = null;

		// use loop to deal with the ResultSet
		while (resultSet.next()) {
			map = new HashMap<>();
			for (String columnLabelStr : columnLabels) {
				Object columnValue = resultSet.getObject(columnLabelStr);
				map.put(columnLabelStr, columnValue);
			}
			// add the Map record to the list
			values.add(map);
		}
		return values;
	}

	/**
	 * achieve the columnLabel list of the resultSetMetaData
	 * 
	 * @param resultSet
	 * @return
	 * @throws Exception
	 */
	private List<String> getColumnLabels(ResultSet resultSet) throws Exception {
		List<String> labels = new ArrayList<String>();

		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
			labels.add(resultSetMetaData.getColumnLabel(i + 1));
		}
		return labels;
	}
}
