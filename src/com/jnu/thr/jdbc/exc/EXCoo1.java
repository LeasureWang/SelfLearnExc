package com.jnu.thr.jdbc.exc;

import java.sql.ResultSet;
import java.util.Scanner;

import org.junit.Test;

import com.jnu.thr.jdbc.JdbcTools;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class EXCoo1 {

	@Test
	public void testAdd() throws Exception {
		Student student = getStudentInfoFromConslie();
		addStudentInfo2(student);
	}
	 
	/*
	 * insert student onfo from console
	 */
	public Student getStudentInfoFromConslie() {

		Scanner scanner = new Scanner(System.in);
		Student student = new Student();

		// get the values of field from console
		System.out.print("FolwId:");
		student.setFolwID(scanner.nextInt());
		System.out.print("Type:");
		student.setType(scanner.nextInt());
		System.out.print("IdCard:");
		student.setIdCard(scanner.nextInt());
		System.out.print("ExamCard:");
		student.setExamCard(scanner.nextInt());
		System.out.print("StudentName:");
		student.setStudentName(scanner.next());
		System.out.print("Location:");
		student.setLocation(scanner.next());
		System.out.print("Grade:");
		student.setGrade(scanner.nextInt());

		return student;
	}

	/*public void addStudentInfo(Student student) throws Exception {

		// execution sql
		String sql = "INSERT INTO student VALUES(" + student.getFolwID() + ","
				+ student.getType() + "," + student.getIdCard() + ","
				+ student.getExamCard() + ",'" + student.getStudentName()
				+ "','" + student.getLocation() + "'," + student.getGrade()
				+ ")";

		System.out.println(sql);
		JdbcToolsExcoo1.update(sql);

	}*/
	public void addStudentInfo2(Student student) throws Exception {

		// execution sql
		String sql = "INSERT INTO student VALUES(" + student.getFolwID() + ","
				+ student.getType() + "," + student.getIdCard() + ","
				+ student.getExamCard() + ",'" + student.getStudentName()
				+ "','" + student.getLocation() + "'," + student.getGrade()
				+ ")";

		System.out.println(sql);
		JdbcToolsExcoo1.update2(sql);

	}
}
