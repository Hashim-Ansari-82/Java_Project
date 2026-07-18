package com.entity;

public class Student {

	private Integer studentId;
	private String studentName;
	private String studentEmail;
	private String studentCourse;
	private Integer studentAge;
	private String studentCity;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(String studentCourse) {
		this.studentCourse = studentCourse;
	}

	public Integer getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}

	public String getStudentCity() {
		return studentCity;
	}

	public void setStudentCity(String studentCity) {
		this.studentCity = studentCity;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail
				+ ", studentCourse=" + studentCourse + ", studentAge=" + studentAge + ", studentCity=" + studentCity
				+ "]";
	}

	public Student(Integer studentId, String studentName, String studentEmail, String studentCourse, Integer studentAge,
			String studentCity) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentCourse = studentCourse;
		this.studentAge = studentAge;
		this.studentCity = studentCity;
	}

	public Student() {
		super();
	}

}
