package com.dao;

import com.entity.Student;

public interface StudentDao {

	public void addStudent(Student student);

	void updateStudent(Student student);

	void deleteStudent(Integer id);

	void getStudentById(Integer id);

	void getAllStudents();

	void searchStudentByName(String name);

}
