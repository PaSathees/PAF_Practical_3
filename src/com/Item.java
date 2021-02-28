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
			String query = "insert into items (itemID, itemCode, itemName, itemPrice, itemDesc)"
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
	
	public String readItems() {
		String output = "";
		
		try {
			Connection con = this.connect();
			if (con == null) {
				return "Error while connecting to database for reading";
			}
			
			//preparing HTML table as for output
			output = "<table border='1'>"
					+ "<tr>"
					+ "<th>Item Code</th>"
					+ "<th>Item Name</th>"
					+ "<th>Item Price</th>"
					+ "<th>Item Description</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>";
			
			String query = "select * from items";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//iterate over each row in the result set
			while (rs.next()) {
				String itemID = Integer.toString(rs.getInt("itemID"));
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String itemPrice = rs.getString("itemPrice");
				String itemDesc = rs.getString("itemDesc");
				
				//add retrieved values to output table
				output += "<tr><td>" + itemCode + "</td>";
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemPrice + "</td>";
				output += "<td>" + itemDesc + "</td>";
				
				output += "<td><input name='btnUpdate' type='button' value='Update'></td>"
						+ "<td><form method='post' action='item.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'>"
						+ "<input name='itemID' type='hidden' value='" + itemID + "'>"
						+ "</form></td></tr>";
			}
			
			con.close();
			output += "</table>";
			
		} catch (Exception e) {
			output = "Error while reading the items";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
}
