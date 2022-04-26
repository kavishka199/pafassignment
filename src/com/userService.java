package com;

import model.users;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")
public class userService {
	
	users userObj = new users();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers() {
		
		return userObj.readUsers();
		//return "Hello";
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertUsers(@FormParam("userID") String userID,
							@FormParam("userName") String userName,
							@FormParam("userPh_No") String userPh_No,
							@FormParam("userEmail") String userEmail)
	{
		
		String output = userObj.insertUsers(userID, userName, userPh_No, userEmail);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUsers(String userData)
	{
		//Convert input string to a JSON object
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		
		//Read values from JSON object
		String userID = userObject.get("userID").getAsString();
		String userName = userObject.get("userName").getAsString();
		String userPh_No = userObject.get("userPh_No").getAsString();
		String userEmail = userObject.get("userEmail").getAsString();
		
		String output = userObj.updateUser(userID, userName, userPh_No, userEmail);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUsers(String userData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String userID = doc.select("userID").text();
		
		String output = userObj.deleteUser(userID);
		return output;
		
	}

}
