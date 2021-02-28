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
	
	
	public String insertItem(String code, String name, String price, String desc) {
		String output = "";
		
		try {
			Connection con = this.connect();
			
			if (con == null) {
				return "Error connecting to database";
			}
			
			//creating prepared statement
			String query = "insert into items ('itemID', 'itemCode', 'itemName', 'itemPrice', 'itemDesc')"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values to prepared statement
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, desc);
			
			preparedStmt.execute();
			con.close();
			
			output = "inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		return output;		
	}
}
