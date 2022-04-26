package com;

import model.interruption;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Interruption")
public class interruptionService {
	
	interruption interruptionObj = new interruption();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInterruptions() {
		
		return interruptionObj.readInterruptions();
		//return "Hello";
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertInterruptions(@FormParam("interruptionID") String interruptionID,
							@FormParam("interruptionName") String interruptionName,
							@FormParam("interruptionDate") String interruptionDate,
							@FormParam("interruptionAdd") String interruptionAdd)
	{
		
		String output = interruptionObj.insertInterruptions(interruptionID, interruptionName, interruptionDate, interruptionAdd);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateinterruptions(String interruptionData)
	{
		//Convert input string to a JSON object
		JsonObject interruptionObject = new JsonParser().parse(interruptionData).getAsJsonObject();
		
		//Read values from JSON object
		String interruptionID = interruptionObject.get("interruptionID").getAsString();
		String interruptionName = interruptionObject.get("interruptionName").getAsString();
		String interruptionDate = interruptionObject.get("interruptionDate").getAsString();
		String interruptionAdd = interruptionObject.get("interruptionAdd").getAsString();
		
		String output = interruptionObj.updateInterruption(interruptionID, interruptionName, interruptionDate, interruptionAdd);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInterruptions(String interruptionData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(interruptionData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String interruptionID = doc.select("interruptionID").text();
		
		String output = interruptionObj.deleteInterruption(interruptionID);
		return output;
		
	}

}
