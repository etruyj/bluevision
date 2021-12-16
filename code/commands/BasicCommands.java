package com.socialvagrancy.bluevision.commands;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.socialvagrancy.bluevision.structures.*;
import com.socialvagrancy.bluevision.utils.Connector;

import com.socialvagrancy.utils.Logger;

public class BasicCommands
{
	private String token;
	private Logger logbook;

	public BasicCommands(Logger logs)
	{
		logbook = logs;
	}

	public MailSlotStatus[] mailslotStatus(String ipaddress, String port)
	{
		Gson gson = new Gson();
		Connector conn = new Connector();
		String url = "";

		url = URLs.mailslotStatusURL(ipaddress, port);
		logbook.logWithSizedLogRotation("Getting mail lost status...", 1);
		logbook.logWithSizedLogRotation("GET " + url, 2);

		String response = conn.GET(url, token);

		logbook.logWithSizedLogRotation("Server response: " + response, 2);
		
		try
		{
			logbook.logWithSizedLogRotation("Status queried successfully", 2);
			MailSlotStatus[] mailslots = gson.fromJson(response, MailSlotStatus[].class);

			return mailslots;
		}	
		catch(JsonParseException e)
		{
			logbook.logWithSizedLogRotation("Unable to query mailslot status", 2);
			logbook.logWithSizedLogRotation(e.getMessage(), 3);
			
			return null;
		}
	}

	public String getToken()
	{
		return token;
	}

	public String inventoryScan(String ipaddress, String port)
	{
		String url = URLs.inventoryScanURL(ipaddress, port);

		logbook.logWithSizedLogRotation("Scanning inventory...", 1);
		logbook.logWithSizedLogRotation("POST " + url, 2);
		
		Connector conn = new Connector();

		String response = conn.POST(url, token, "");

		if(response.length()>0)
		{
			logbook.logWithSizedLogRotation("Failed to scan inventory", 2);
			logbook.logWithSizedLogRotation(response, 3);
		}
		else
		{
			logbook.logWithSizedLogRotation("Starting inventory scan", 2);
		}

		return response;
	}

	public Inventory libraryInventory(String ipaddress, String port, String partition)
	{
		Gson gson = new Gson();

		String url = URLs.libraryInventory(ipaddress, port);

		logbook.logWithSizedLogRotation("Checking inventory...", 1);
		logbook.logWithSizedLogRotation("GET " + url, 2);
		logbook.logWithSizedLogRotation("Partition specified: " + partition, 2);

		Connector conn = new Connector();

		String response = conn.GET(url, token);

		try
		{
			return gson.fromJson(response, Inventory.class);
		}
		catch(JsonParseException e)
		{
			System.err.println(e.getMessage());

			return new Inventory();
		}
	}

	public boolean login(String ipaddress, String port, String username, String password)
	{
		Gson gson = new Gson();

		String url = URLs.loginURL(ipaddress, port);

		logbook.logWithSizedLogRotation("Logging in...", 1);
		logbook.logWithSizedLogRotation("POST " + url, 2);
		logbook.logWithSizedLogRotation("Username: " + username, 2);

		String auth = "{ \"username\":\"" 
			+ username + "\", \"password\":\"" + password
			+ "\" }";

		Connector conn = new Connector();

		String response = conn.POST(url, auth);
	
	
		if(response.equals("") || response == null)
		{
			logbook.logWithSizedLogRotation("Login FAILED. Invalid credentials", 2);
			return false;
		}
		else
		{		
			try
			{

				Token tok = gson.fromJson(response, Token.class);
			
				token = tok.getToken();

				logbook.logWithSizedLogRotation("Login SUCCESSFUL", 2);

				return true;
			}
			catch(JsonParseException e)
			{
				logbook.logWithSizedLogRotation("ERROR: " + e.getMessage(), 3);
				logbook.logWithSizedLogRotation("Login FAILED", 2);

				return false;
			}
		}
		
	}

	public String moveMedia(String ipaddress, String port, MoveDetails move)
	{
		Gson gson = new Gson();
		Connector conn = new Connector();

		String url = URLs.moveMediaURL(ipaddress, port);
		String body = gson.toJson(move);

		logbook.logWithSizedLogRotation("Issuing move command...", 1);
		logbook.logWithSizedLogRotation("POST " + url, 2);
		logbook.logWithSizedLogRotation("Moving " + move.SrcType + ":" + move.SrcAddress + " to " + move.DestType + ":" + move.DestAddress, 2);
		
		String response = conn.POST(url, token, body);
		
		if(response.length()==0)
		{
			logbook.logWithSizedLogRotation("Move SUCCESSFUL", 2);
			return "[SUCCESS]";
		}
		else
		{
			logbook.logWithSizedLogRotation("Move FAILED", 2);
			return	"[FAILED]";
		}	
	}

	public String openMailslots(String ipaddress, String port, String module_num)
	{
		Connector conn = new Connector();
		Gson gson = new Gson();

		String url = URLs.openMailslotURL(ipaddress, port);

		String body = "{\"module\": " + module_num + " }";

		logbook.logWithSizedLogRotation("Opening mail slots...", 1);
		logbook.logWithSizedLogRotation("POST " + url, 2);
		logbook.logWithSizedLogRotation("body: " + body, 2);

		String response = conn.POST(url, token, body);

		if(response.length() > 0)
		{
			logbook.logWithSizedLogRotation("Failed to open mail slots", 3);
			logbook.logWithSizedLogRotation(response, 3);

			return response;
		}	
		else
		{
			logbook.logWithSizedLogRotation("Mail slots opened", 2);

			return "Mail slots are unlocked.";
		}
	}


	public PartitionInfo[] partitionInfo(String ipaddress, String port)
	{
		Gson gson = new Gson();

		String url = URLs.partitionInfoURL(ipaddress, port);

		Connector conn = new Connector();
		
		logbook.logWithSizedLogRotation("Requesting partition information...", 1);
		logbook.logWithSizedLogRotation("GET " + url, 2);
		
		String response = conn.GET(url, token);

		try
		{
			PartitionInfo[] partitions = gson.fromJson(response, PartitionInfo[].class);
			
			return partitions;
		}
		catch(JsonParseException e)
		{
			System.err.println(e.getMessage());
			return new PartitionInfo[0];
		}
	}	
	
	public Inventory partitionInventory(String ipaddress, String port, String partition_num)
	{
		Gson gson = new Gson();

		String url = URLs.partitionInventoryURL(ipaddress, port, partition_num);

		Connector conn = new Connector();
		
		logbook.logWithSizedLogRotation("Requesting inventory for partition " + partition_num + "...", 1);
		logbook.logWithSizedLogRotation("GET " + url, 2);
	
		String response = conn.GET(url, token);

		try
		{
			Inventory inventory = gson.fromJson(response, Inventory.class);
			
			return inventory;
		}
		catch(JsonParseException e)
		{
			System.err.println(e.getMessage());
			return new Inventory();
		}
	}

	public String reboot(String ipaddress, String port)
	{
		String url = URLs.rebootLibraryURL(ipaddress, port);

		logbook.logWithSizedLogRotation("Rebooting library...", 1);
		logbook.logWithSizedLogRotation("POST " + url, 2);
		
		Connector conn = new Connector();

		String response = conn.POST(url, token, "");

		if(response.length()>0)
		{
			logbook.logWithSizedLogRotation("Failed to reboot library", 2);
			logbook.logWithSizedLogRotation(response, 3);
		}
		else
		{
			logbook.logWithSizedLogRotation("rebooting...", 2);
		}

		return response;
	}

	public String shutdown(String ipaddress, String port)
	{
		String url = URLs.shutdownLibraryURL(ipaddress, port);

		logbook.logWithSizedLogRotation("Shutting down library...", 1);
		logbook.logWithSizedLogRotation("POST " + url, 2);
		
		Connector conn = new Connector();

		String response = conn.POST(url, token, "");

		if(response.length()>0)
		{
			logbook.logWithSizedLogRotation("Failed to shutdown library", 2);
			logbook.logWithSizedLogRotation(response, 3);
		}
		else
		{
			logbook.logWithSizedLogRotation("Shutting down", 2);
		}

		return response;
	}
}
