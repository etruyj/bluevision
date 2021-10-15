package com.socialvagrancy.bluevision.commands;

public class URLs
{

	public static String libraryInventory(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/library/inventory";
	}

	public static String loginURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/login";
	}

	public static String moveMediaURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/library/movemedia";
	}
	
	public static String partitionInfoURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/partition/information";
	}
}
