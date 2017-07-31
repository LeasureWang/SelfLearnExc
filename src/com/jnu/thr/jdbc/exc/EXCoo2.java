package com.jnu.thr.jdbc.exc;

import java.sql.ResultSet;
import java.util.Scanner;

import org.junit.Test;

import com.jnu.thr.jdbc.JdbcTools;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class EXCoo2 {

	@Test
	public void testAchieveStudentInfo() {

		int searchKey = getSearchKeyFromConsole();

		Student student = searchStuTable(searchKey);

		printAchievedStuInfo(student);
	}

	/*
	 * print the student info in console
	 */
	private void printAchievedStuInfo(Student student) {

		if (student != null) {
			System.out.println("[info in the below:]");
			System.out.println(student);
		} else {
			System.out.println("cannot search this person!");
		}
	}

	/*
	 * make the sql and search in db
	 */
	private Student searchStuTable(int searchKey) {

		String sql = "select folwID,Type,IDCard,ExamCard,StudentName,Location,Grade from student where ";
		Scanner scanner = new Scanner(System.in);
		if (searchKey == 1) {
			System.out.println("entry IDCard");
			int idCard = scanner.nextInt();
			sql = sql + "IDCard=" + idCard + "";
		} else {
			System.out.println("entry ExamCard");
			int examCard = scanner.nextInt();
			sql = sql + "ExamCard=" + examCard + "";
		}
		Student student = achieveStuInfo(sql);
		return student;
	}

	/*
	 * search in db
	 * get the student info from the db
	 */
	private Student achieveStuInfo(String sql) {

		Student student = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = (Connection) JdbcToolsExcoo1.dbConnection();
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				student = new Student(resultSet.getInt(1), resultSet.getInt(2),
						resultSet.getInt(3), resultSet.getInt(4),
						resultSet.getNString(5), resultSet.getString(6),
						resultSet.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcToolsExcoo1.release(resultSet, statement, connection);
		}

		return student;
	}

	/*
	 * enrty the key type which can help you choose the search type
	 */
	private int getSearchKeyFromConsole() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("[-------please write the key-----]");
		System.out.println("|		1:IDCard ~~	2:ExamCard		|");
		int searchKey = scanner.nextInt();
		if (searchKey != 1 && searchKey != 2) {
			System.out.println("error entry!!");
			throw new RuntimeException();
		}
		return searchKey;
	}


}
