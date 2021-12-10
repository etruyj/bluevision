package com.socialvagrancy.bluevision.commands;

public class URLs
{
	public static String inventoryScanURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/library/inventory";
	}
	
	public static String libraryInventory(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/library/inventory";
	}

	public static String loginURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/login";
	}

	public static String mailslotStatusURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/library/io/status";
	}

	public static String moveMediaURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/library/movemedia";
	}
	
	public static String openMailslotURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/library/io/open";
	}

	public static String partitionInfoURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/partition/information";
	}

	public static String partitionInventoryURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/partition/inventory";
	}

	public static String partitionMedia(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/partition/mediainfo";
	}

	public static String rebootLibraryURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/library/reboot";
	}

	public static String shutdownLibraryURL(String ipaddress, String port)
	{
		return "https://" + ipaddress + ":" + port + "/rest/library/powerdown";
	}
}
