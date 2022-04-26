package model;

import java.sql.*;

public class users {
	
	public String insertUsers(String ID, String Name, String Ph_No, String Email) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for inserting";
				
			}
			
			String query = "insert into inquiries values ( ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,ID);
			preparedStmt.setString(2,Name);
			preparedStmt.setString(3,Ph_No);
			preparedStmt.setString(4,Email);
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Inserted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while inserting";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String readUsers() {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return "Error while connecting to the database for reading";
				
			}
			
			//HTML table
			output = "<table border=\"1\">\r\n"
					+ "		<tr>\r\n"
					+ "			<th>User ID</th><th>User Name</th><th>User Ph_No</th><th>User Email</th><th>Update</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from users";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				String userID = Integer.toString(rs.getInt("userID"));
				String userName = rs.getString("userName");
				String userPh_No = rs.getString("userPh_No");
				String userEmail = Double.toString(rs.getDouble("userEmail"));
				
				//add a row into the html table
				output += "<tr>"+ "<td>" +userID+ "</td><td>" +userName+ "</td><td>" +userPh_No+ "</td><td>" +userEmail+ "</td>"
				+ "<td><form method='post' action='Home.jsp'>"
				+ "<input name='btnUpdate' type='submit' value='Update'>"
				+ "<input name='userID' type='hidden' value='"+userID+"'>"
						+ "<input name='userName' type='hidden' value='"+userName+"'>"
								+ "<input name='userPh_No' type='hidden' value='"+userPh_No+"'>"
										+ "<input name='userEmail' type='hidden' value='"+userEmail+"'>"
												+ "</form></td>"+ "<td><form method='post' action='Home.jsp'>"
														+ "<input name='btnRemove' type='submit' value='Remove'>"
														+ "<input name='userID' type='hidden' value='"+userID+"'>"
																+ "<input name='userName' type='hidden' value='"+userName+"'>"
																		+ "</form></td>"+ "</tr>";
				
			}
			
			con1.close();
			
			//complete the html table
			output += "</table>";
			
			
			
		}
		catch(Exception e) {
			
			output = "Error while reading the users";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String updateUser(String ID, String Name, String Ph_No, String Email) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update users set userID=?, userName=?, userPh_No=?, userEmail=? where userID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,ID);
			preparedStmt.setString(2,Name);
			preparedStmt.setString(3,Ph_No);
			preparedStmt.setString(4,Email);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Updated Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while updating";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String deleteUser(String userID) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for deleting";
				
			}
			
			String query = "delete from users where userID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(userID));
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Deleted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while deleting";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}

}
