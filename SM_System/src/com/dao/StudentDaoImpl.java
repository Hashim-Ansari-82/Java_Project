package com.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.Student;
import com.main.Main;
import com.util.DBConnection;

public class StudentDaoImpl implements StudentDao {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public void addStudent(Student student) {

		try {

			System.out.println("Enter Student Id");
			Integer id = Integer.parseInt(br.readLine());
			System.out.println("Enter Student name");
			String name = br.readLine();
			System.out.println("Enter Student email");
			String email = br.readLine();
			System.out.println("Enter Student course");
			String course = br.readLine();
			System.out.println("Enter Student Age");
			Integer age = Integer.parseInt(br.readLine());
			System.out.println("Enter Student City");
			String city = br.readLine();

			Connection con = DBConnection.getConnection();

			String query = "insert into student values(?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(query);

			st.setInt(1, id);
			st.setString(2, name);
			st.setString(3, email);
			st.setString(4, course);
			st.setInt(5, age);
			st.setString(6, city);

			int i = st.executeUpdate();
			if (i != 0) {
				System.out.println("Data Added Successfully\n");
				Main.menu();
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public void updateStudent(Student student) {

		try {

			System.out.println("Enter Id you can change Detail");
			int id = Integer.parseInt(br.readLine());
			System.out.println("Enter new Student name");
			String name = br.readLine();
			System.out.println("Enter new Student email");
			String email = br.readLine();
			System.out.println("Enter new Student course");
			String course = br.readLine();
			System.out.println("Enter new Student Age");
			Integer age = Integer.parseInt(br.readLine());
			System.out.println("Enter new Student City");
			String city = br.readLine();

			Connection con = DBConnection.getConnection();

			String query = "update student set name=?,email=?,course=?,age=?,city=? where id=?";

			PreparedStatement st = con.prepareStatement(query);

			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, course);
			st.setInt(4, age);
			st.setString(5, city);
			st.setInt(6, id);

			int i = st.executeUpdate();
			if (i != 0) {
				System.out.println("Data Updated Successfully\n");
				Main.menu();
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public void deleteStudent(Integer id) {
		try {
			System.out.println("Enter Student Id You can delete");
			Integer studentId = Integer.parseInt(br.readLine());

			Connection con = DBConnection.getConnection();

			String query = "delete from student where id=?";

			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, studentId);

			int i = st.executeUpdate();
			if (i != 0) {
				System.out.println("\nData deleted Successfully\n");
				Main.menu();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void getStudentById(Integer id) {

		try {
			System.out.println("Enter Student Id You can fetch detail");
			Integer studentId = Integer.parseInt(br.readLine());

			Connection con = DBConnection.getConnection();

			String query = "select * from student where id=?";

			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, studentId);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Student student = new Student();

				student.setStudentId(rs.getInt("id"));
				student.setStudentName(rs.getString("name"));
				student.setStudentEmail(rs.getString("email"));
				student.setStudentCourse(rs.getString("course"));
				student.setStudentAge(rs.getInt("age"));
				student.setStudentCity(rs.getString("city"));

				System.out.println(student);

				System.out.println("\nData Fetch Successfully\n");
				Main.menu();
			} else {
				System.out.println("Record not found in Database");
				Main.menu();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getAllStudents() {
		try {
			Connection con = DBConnection.getConnection();

			String query = "select * from student";

			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			while (rs.next()) {
				Student student = new Student();

				student.setStudentId(rs.getInt("id"));
				student.setStudentName(rs.getString("name"));
				student.setStudentEmail(rs.getString("email"));
				student.setStudentCourse(rs.getString("course"));
				student.setStudentAge(rs.getInt("age"));
				student.setStudentCity(rs.getString("city"));

				System.out.println(student);
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.println("Data Fetch Successfully\n");
			Main.menu();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void searchStudentByName(String name) {
		try {
			System.out.println("Enter Student name You can fetch detail");
			String studentName = br.readLine();

			Connection con = DBConnection.getConnection();

			String query = "select * from student where name=?";

			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, studentName);

			ResultSet rs = st.executeQuery();

			if(!rs.next()) {
			System.out.println("Record not found in database");
			}
			else {
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
				do {
						Student student = new Student();

						student.setStudentId(rs.getInt("id"));
						student.setStudentName(rs.getString("name"));
						student.setStudentEmail(rs.getString("email"));
						student.setStudentCourse(rs.getString("course"));
						student.setStudentAge(rs.getInt("age"));
						student.setStudentCity(rs.getString("city"));

						System.out.println(student);
						System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
				}while(rs.next());
				System.out.println("                                        Data Fetch Successfully\n");
			}
			Main.menu();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
  }
}
