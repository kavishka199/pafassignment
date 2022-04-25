package com;

import model.inquiries;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Inquiries")
public class inquiryService {
	
	inquiries inquiryObj = new inquiries();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInquiries() {
		
		return inquiryObj.readInquiries();
		//return "Hello";
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertInquiries(@FormParam("inquiryID") String inquiryID,
							@FormParam("inquiryType") String inquiryType,
							@FormParam("inquiryDate") String inquiryDate,
							@FormParam("inquiryDescription") String inquiryDescription)
	{
		
		String output = inquiryObj.insertInquiries(inquiryID, inquiryType, inquiryDate, inquiryDescription);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInquiries(String inquiryData)
	{
		//Convert input string to a JSON object
		JsonObject inquiryObject = new JsonParser().parse(inquiryData).getAsJsonObject();
		
		//Read values from JSON object
		String inquiryID = inquiryObject.get("inquiryID").getAsString();
		String inquiryType = inquiryObject.get("inquiryType").getAsString();
		String inquiryDate = inquiryObject.get("inquiryDate").getAsString();
		String inquiryDescription = inquiryObject.get("inquiryDescription").getAsString();
		
		String output = inquiryObj.updateInquiry(inquiryID, inquiryType, inquiryDate, inquiryDescription);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiries(String inquiryData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String inquiryID = doc.select("inquiryID").text();
		
		String output = inquiryObj.deleteInquiry(inquiryID);
		return output;
		
	}

}
