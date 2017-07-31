package com.jnu.thr.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mysql.jdbc.ResultSetMetaData;

public class TestResultSetMetaData {

	@Test
	public void testResultSetMetaData() {

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			String sql = "select cutr_key customerKey,cutr_name customerName,cutr_location customerLocation,cutr_tel customerTel from tb_customer where cutr_key=?";
			connection=JdbcTools.dbConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,1008616);
			resultSet=preparedStatement.executeQuery();
			Map<String,Object> values=new HashMap<String,Object>();
			
			//achieve the ResultSetMetaData object
			ResultSetMetaData resultSetMetaData=(ResultSetMetaData) resultSet.getMetaData();
			
			//print the every column name
			while(resultSet.next()){
				for(int i=0;i<resultSetMetaData.getColumnCount();i++){
					String columnLabel =resultSetMetaData.getColumnLabel(i+1);
					Object columnValue=resultSet.getObject(columnLabel);
					values.put(columnLabel, columnValue);
				}
			}
			
			Class clazz=Customer.class;
			Object object=clazz.newInstance();
			for(Map.Entry<String, Object> entry: values.entrySet()){
				String fieldName=entry.getKey();
				Object fieldValue=entry.getValue();
				//System.out.println(fieldName+"-->"+fieldValue);
				ReflectionUtils.setFieldValue(object, fieldName, fieldValue);
			}
			System.out.println(object);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcTools.release(resultSet, preparedStatement, connection);
		}
	}
}
