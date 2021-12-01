//===================================================================
//	BlueController.java
//		Description:
//			This is the controller for the BlueVision
//			commands defined in the commands/ directory.
//			This class abstracts the individual command
//			layer and performs necessary translations
//			between the interface layer and the command
//			layer.
//===================================================================

package com.socialvagrancy.bluevision.utils;

import com.socialvagrancy.bluevision.commands.AdvancedCommands;
import com.socialvagrancy.bluevision.commands.BasicCommands;

import com.socialvagrancy.bluevision.structures.Inventory;
import com.socialvagrancy.bluevision.structures.MoveDetails;
import com.socialvagrancy.bluevision.structures.PartitionInfo;
import com.socialvagrancy.bluevision.structures.Token;

import com.socialvagrancy.utils.Logger;

import java.util.ArrayList;

public class BlueController
{
	private BasicCommands library;
	private AdvancedCommands advanced;
	private String token;
	private Logger logbook;

	public BlueController()
	{
		logbook = new Logger("../logs/bluevision.log", 10240, 1, 1);
		library = new BasicCommands(logbook);
		advanced = new AdvancedCommands(library, logbook);
	}

	public void ejectTapes(String ip_address, String port, String partition, String file_name, boolean printToShell)
	{
		String partition_num = partition;
		advanced.ejectListedTapes(ip_address, port, partition_num, file_name, printToShell);
	}

	public String inventoryScan(String ip_address, String port)
	{
		return library.inventoryScan(ip_address, port);
	}

	public Inventory listInventory(String ip_address, String port, String partition)
	{
		String partition_num = partition;

		return library.libraryInventory(ip_address, port, partition_num);
	}

	public ArrayList<String> listPartitions(String ip_address, String port)
	{
		return advanced.listPartitions(ip_address, port);
	}

	public boolean login(String ip_address, String port, String username, String password)
	{
		return library.login(ip_address, port, username, password);
	}

	public String moveMedia(String ip_address, String port, String source, String source_address, String target, String target_address)
	{
		MoveDetails move = new MoveDetails();
	
		move.SrcType = source;
		move.SrcAddress = source_address;
		move.DestType = target;
		move.DestAddress = target_address;

		String responseLine = "Move: " + move.SrcType + ":" + move.SrcAddress 
			+ " > " + move.DestType + ":" + move.DestAddress + "...\t";

		return responseLine + library.moveMedia(ip_address, port, move);
	}

	public void moveList(String ip_address, String port, String file_name, String partition)
	{
		String partition_num = partition;

		advanced.moveList(ip_address, port, partition_num, file_name);
	}

	public PartitionInfo[] partitionInfo(String ip_address, String port)
	{
		return library.partitionInfo(ip_address, port);
	}

	public void protectMailSlots(String ip_address, String port, String partition, boolean printToShell)
	{
		advanced.protectMailSlotTapes(ip_address, port, partition, printToShell);
	}

	public String rebootLibrary(String ip_address, String port)
	{
		return library.reboot(ip_address, port);
	}

	public String shutdownLibrary(String ip_address, String port)
	{
		return library.shutdown(ip_address, port);
	}
}
