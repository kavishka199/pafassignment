package model;

import java.sql.*;

public class inquiries {
	
	public String insertInquiries(String ID, String Type, String Date, String Description) {
		
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
			preparedStmt.setString(2,Type);
			preparedStmt.setString(3,Date);
			preparedStmt.setString(4,Description);
			
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
	
	public String readInquiries() {
		
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
					+ "			<th>Inquiry ID</th><th>Inquiry Type</th><th>Inquiry Date</th><th>Inquiry Description</th><th>Update</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from inquiries";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				String inquiryID = Integer.toString(rs.getInt("inquiryID"));
				String inquiryType = rs.getString("inquiryType");
				String inquiryDate = rs.getString("inquiryDate");
				String inquiryDescription = Double.toString(rs.getDouble("inquiryDescription"));
				
				//add a row into the html table
				output += "<tr>"+ "<td>" +inquiryID+ "</td><td>" +inquiryType+ "</td><td>" +inquiryDate+ "</td><td>" +inquiryDescription+ "</td>"
				+ "<td><form method='post' action='Home.jsp'>"
				+ "<input name='btnUpdate' type='submit' value='Update'>"
				+ "<input name='inquiryID' type='hidden' value='"+inquiryID+"'>"
						+ "<input name='inquiryType' type='hidden' value='"+inquiryType+"'>"
								+ "<input name='inquiryDate' type='hidden' value='"+inquiryDate+"'>"
										+ "<input name='inquiryDescription' type='hidden' value='"+inquiryDescription+"'>"
												+ "</form></td>"+ "<td><form method='post' action='Home.jsp'>"
														+ "<input name='btnRemove' type='submit' value='Remove'>"
														+ "<input name='inquiryID' type='hidden' value='"+inquiryID+"'>"
																+ "<input name='inquiryType' type='hidden' value='"+inquiryType+"'>"
																		+ "</form></td>"+ "</tr>";
				
			}
			
			con1.close();
			
			//complete the html table
			output += "</table>";
			
			
			
		}
		catch(Exception e) {
			
			output = "Error while reading the inquiries";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String updateInquiry(String ID, String Type, String Date, String Description) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update inquiries set inquiryID=?, inquiryType=?, inquiryDate=?, inquiryDescription=? where inquiryID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,ID);
			preparedStmt.setString(2,Type);
			preparedStmt.setString(3,Date);
			preparedStmt.setString(4,Description);
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
	
	public String deleteInquiry(String inquiryID) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for deleting";
				
			}
			
			String query = "delete from inquiries where inquiryID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(inquiryID));
			
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
