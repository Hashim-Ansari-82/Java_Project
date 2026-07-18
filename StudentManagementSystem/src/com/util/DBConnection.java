package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection()  throws Exception {
		
		String url="jdbc:mysql://localhost:3306/SM";
		String password="root";
		String userName="root";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
        Connection con = DriverManager.getConnection(url,userName,password);
        
        return con;
		
	}
}
