package com.jnu.thr.jdbc.normal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * the DAO interface of access to the db
 * many different methods to access to the db
 * 
 * @author LEASURE_WANG
 * @param: the entity of the dao will be done
 */
public interface DAO<T> {

	/**
	 * the batch of more working in time
	 * @param connection
	 * @param sql
	 * @param args
	 */
	void batch(Connection connection, String sql, Object...args);
	
	
	/**
	 * return the real value
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	<E> E getForValue(Connection connection, String sql, Object...args);
	
	
	/**
	 * return one list of T
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	List<T> getForList(Connection connection, String sql, Object...args);
	
	
	/**
	 * return one object of T
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	T get(Connection connection, String sql, Object...args) throws SQLException;
	
	
	/**
	 * insert, update, delete
	 * @param connection: connection of the db
	 * @param sql: sql
	 * @param args: the args of fill the placeholder
	 */
	void update(Connection connection, String sql, Object...args);
	
}
