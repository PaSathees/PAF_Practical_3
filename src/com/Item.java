package com;
import java.sql.*;

public class Item {
	public Connection connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_lab3", "root", "");
			System.out.println("Connection successfully established");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
