package com.jnu.thr.jdbc.exc;

public class Student {

	private int folwID;
	private int type;
	private int idCard;
	private int examCard;
	private String studentName;
	private String location;
	private int grade;

	public int getFolwID() {
		return folwID;
	}

	public void setFolwID(int folwID) {
		this.folwID = folwID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}

	public int getExamCard() {
		return examCard;
	}

	public void setExamCard(int examCard) {
		this.examCard = examCard;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Student(int folwID, int type, int idCard, int examCard,
			String studentName, String location, int grade) {
		super();
		this.folwID = folwID;
		this.type = type;
		this.idCard = idCard;
		this.examCard = examCard;
		this.studentName = studentName;
		this.location = location;
		this.grade = grade;
	}

	public Student() {
		super();
	}

	@Override
	public String toString() {
		return "Student [folwID=" + folwID + ", type=" + type + ", idCard="
				+ idCard + ", examCard=" + examCard + ", studentName="
				+ studentName + ", location=" + location + ", grade=" + grade
				+ "]";
	}
	
	public void studying(){
		System.out.println("students are studying!!");
	}

}
