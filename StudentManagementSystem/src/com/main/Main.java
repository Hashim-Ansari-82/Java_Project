package com.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.dao.StudentDaoImpl;
import com.entity.Student;

public class Main {

	public static void menu() {
		try {
			StudentDaoImpl dao = new StudentDaoImpl();

			Student student = new Student();
			Integer id = student.getStudentId();
			String name = student.getStudentName();

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("             Choose any Operation\n");
			System.out.println("1. Add Student               2. Update Student\n");
			System.out.println("3. Delete Student            4. Get Student by Id\n");
			System.out.println("5. Get all Student           6. Search Student by Name\n");
			System.out.println("             7. Exit");

			int choice = Integer.parseInt(br.readLine());
			switch (choice) {
			case 1:
				dao.addStudent(student);
				break;
			case 2:
				dao.updateStudent(student);
				break;
			case 3:
				dao.deleteStudent(id);
				break;
			case 4:
				dao.getStudentById(id);
				break;
			case 5:
				dao.getAllStudents();
				break;
			case 6:
				dao.searchStudentByName(name);
			default:
				System.out.println("Thank you visit again");
				System.exit(0);
                break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) throws Exception {
     menu();
	}
}
