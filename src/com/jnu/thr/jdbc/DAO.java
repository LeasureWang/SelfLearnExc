package com.jnu.thr.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class DAO {

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

		T entity = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// achieve the resultSet
			connection = JdbcTools.dbConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();

			// achieve resultSetMetaDData
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			// create one Map to put columnLabel and columName
			Map<String, Object> map = new HashMap<String, Object>();
			// use the resultSetMetaData to fill the Map
			if (resultSet.next()) {
				for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
					String columnLabel = resultSetMetaData
							.getColumnLabel(i + 1);
					Object columnValue = resultSet.getObject(i + 1);
					map.put(columnLabel, columnValue);
				}
			}
			//create the Object use Reflection when the Map is not null
			if(map.size()>0){
				entity=clazz.newInstance();
				//set the values of filed use the reflection Class
				for(Map.Entry<String,Object> entry:map.entrySet()){
					String fieldName=entry.getKey();
					Object fieldValue=entry.getValue();
					ReflectionUtils.setFieldValue(entity, fieldName, fieldValue);	
					System.out.println("	|successfully!!|	");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.release(resultSet, preparedStatement, connection);
		}
		
		//return the result
		return entity;
	}

	// return the list of the Object what have many records
	public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
		
		List<T> list=new ArrayList<>();
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			
			connection =JdbcTools.dbConnection();
			preparedStatement=connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				preparedStatement.setObject(i+1, args[i]);
			}
			resultSet=preparedStatement.executeQuery();
			
			//prepare a List<Map<String,Object>> for record, one Map one record
			List<Map<String,Object>> values=new ArrayList<>();
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			Map<String,Object> map=null;
			
			//use loop to deal with the ResultSet
			while(resultSet.next()){
				map=new HashMap<>();
				for(int i=0;i<resultSetMetaData.getColumnCount();i++){
					String columnLabel=resultSetMetaData.getColumnLabel(i+1);
					Object columnValue=resultSet.getObject(i+1);
					map.put(columnLabel, columnValue);
				}
				//add the Map record to the list
				values.add(map);
			}
			
			//verify the List is not null and achieve the every Map Object and than reflect the Class
			T bean=null;
			if(values.size()>0){
				for(Map<String,Object> m:values){
					bean=clazz.newInstance();
					for(Map.Entry<String, Object> entry:m.entrySet()){
						String propertyName=entry.getKey();
						Object value=entry.getValue();
						BeanUtils.setProperty(bean, propertyName, value);
					}
					//add to the list
					list.add(bean);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcTools.release(resultSet, preparedStatement, connection);
		}
		
		//return the result
		return list;
	}

	// return one value of the field or others' values or how many records
	public <E> Object getForValue(String sql, Object... args) {
		

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// achieve the resultSet
			connection = JdbcTools.dbConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				return (E) resultSet.getObject(1);
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcTools.release(resultSet, preparedStatement, connection);
		}
		return null;
	}

}
