package com.socialvagrancy.bluevision.commands;

import com.socialvagrancy.bluevision.commands.BasicCommands;
import com.socialvagrancy.bluevision.commands.sub.BarcodeSlot;
import com.socialvagrancy.bluevision.commands.sub.EjectTapes;
import com.socialvagrancy.bluevision.commands.sub.MailSlots;
import com.socialvagrancy.bluevision.commands.sub.MoveList;
import com.socialvagrancy.bluevision.commands.sub.RansomProtect;
import com.socialvagrancy.bluevision.structures.Inventory;
import com.socialvagrancy.bluevision.structures.PartitionInfo;
import com.socialvagrancy.bluevision.structures.MoveDetails;

import com.socialvagrancy.utils.Logger;

import java.util.ArrayList;

public class AdvancedCommands
{
	BasicCommands library;
	Logger logbook;

	public AdvancedCommands(BasicCommands api, Logger logs)
	{
		library = api;
		logbook = logs;
	}

	public void ejectListedTapes(String ipaddress, String port, String partition, String file_name, boolean printToShell)
	{
		ArrayList<String> mailslots;
		ArrayList<MoveDetails> move_list;

		logbook.logWithSizedLogRotation("Ejecting tapes specified in " + file_name + "...", 1);
		logbook.logWithSizedLogRotation("Locating entry/exit slots...", 1);

		if(printToShell)
		{
			System.out.println("Ejecting tapes specified in " + file_name + "...");
			System.out.println("Locating entry/exit slots...");
		}

		mailslots = MailSlots.findEmpty(library, ipaddress, port, partition);


		if(mailslots.size()>0)
		{
			logbook.logWithSizedLogRotation("Importing source location from file...", 1);
		
			if(printToShell)
			{
				System.out.println("Importing source location from file...");
			}

			move_list = EjectTapes.prepareMoves(file_name, mailslots);
		
			// Get report.
			MoveDetails report = move_list.get(move_list.size()-1);
			move_list.remove(move_list.size()-1);

			if(report.SrcType.equals("All"))
			{
				
				logbook.logWithSizedLogRotation("All (" + report.SrcAddress 
							+ ") specified tapes will be moved. (" 
							+ Integer.toString(mailslots.size()-Integer.valueOf(report.DestAddress)) + ") entry/exit slots are available.", 1);

				if(printToShell)
				{
					System.out.println("All (" + report.SrcAddress 
							+ ") specified tapes will be moved. (" 
							+ Integer.toString(mailslots.size()-Integer.valueOf(report.DestAddress)) + ") entry/exit slots are available.");
				}
			}
			
			logbook.logWithSizedLogRotation("Sending moves to library...", 1);

			if(printToShell)
			{
				System.out.println("Sending moves to Spectra Stack...");
			}

			sendMoves(ipaddress, port, move_list, partition, true);
		}
		else
		{
			logbook.logWithSizedLogRotation("No entry/exit slots available. Cancelling action.", 2);

			if(printToShell)
			{
				System.out.println("No entry/slots available. Cancelling moves.");
			}
		}
	}

	public ArrayList<String> listPartitions(String ipaddress, String port)
	{
		ArrayList<String> par_list = new ArrayList<String>();

		logbook.logWithSizedLogRotation("Retrieving partition list...", 1);

		PartitionInfo[] partitions = library.partitionInfo(ipaddress, port);

		logbook.logWithSizedLogRotation("Found " + partitions.length + " partitions", 2);

		for(int i=0; i<partitions.length; i++)
		{
			par_list.add(partitions[i].Name);
		}

		return par_list;
	}

	public void moveList(String ipaddress, String port, String partition, String file_path)
	{
		logbook.logWithSizedLogRotation("Processing move list...", 1);

		ArrayList<MoveDetails> move_list = MoveList.importMoves(file_path);
		sendMoves(ipaddress, port, move_list, partition, true);
	}

	public void protectMailSlotTapes(String ipaddress, String port, String partition, boolean printToShell)
	{
		logbook.logWithSizedLogRotation("Moving mail slot tapes to unpartitioned spaced", 1);
		
		Inventory inv = library.libraryInventory(ipaddress, port, partition);

		logbook.logWithSizedLogRotation("Gathering mail slot tapes", 1);
		ArrayList<MoveDetails> move_list = RansomProtect.findMailSlotTapes(inv);

		logbook.logWithSizedLogRotation("Gathering freepool slots", 1);
		ArrayList<String> freepool = RansomProtect.findFreePoolSlots(inv);

		RansomProtect.updateLogs(move_list, freepool, logbook);

		if(printToShell)
		{
			if(move_list.size() <= 0)
			{
				System.out.println("There are no tapes in entry/exit. No moves will be completed.");
			}
			else if(freepool.size()<=0)
			{
				System.out.println("There are no slots available in the freepool. No moves will be completed.");
			}
			else if(move_list.size() > freepool.size())
			{
				System.out.println("There are only " + freepool.size() + " slots in the freepool and " + move_list.size() + " tapes in entry/exit. Tapes will remain in entry/exit once this action completes.");
			}
			
		}

		move_list = RansomProtect.fromMailSlots(move_list, freepool);

		sendMoves(ipaddress, port, move_list, partition, printToShell);
	}

	//==============================================
	// Private functions
	//==============================================
	
	private void sendMoves(String ipaddress, String port, ArrayList<MoveDetails> move_list, String partition, boolean printToShell)
	{
		MoveDetails move; // for barcode conversion.
		ArrayList<String> failed_moves;

		String response;
		for(int i=0; i<move_list.size(); i++)
		{
			// Filter out and convert barcodes to the slot.
			// working with source type, barcode, bar_code, or XXXXXXLX
			if(move_list.get(i).SrcType.equalsIgnoreCase("barcode")
					|| move_list.get(i).SrcType.equalsIgnoreCase("bar_code")
					|| move_list.get(i).SrcAddress.equals("none"))
			{

				Inventory inv = library.libraryInventory(ipaddress, port, partition);
				
				if(move_list.get(i).SrcAddress.equals("none"))
				{
					// Move the barcode over to the Source Type
					move_list.get(i).SrcAddress = move_list.get(i).SrcType;
				}

				logbook.logWithSizedLogRotation("Searching for slot for barcode [" + move_list.get(i).SrcAddress + "]", 1);

				if(printToShell)
				{
					System.out.println("Searching for slot for barcode [" + move_list.get(i).SrcAddress + "]...");
				}

				move = BarcodeSlot.findSlot(inv, move_list.get(i));
				if(!move.SrcType.equals("none"))
				{
					logbook.logWithSizedLogRotation("Barcode found at " + move.SrcType + ":" + move.SrcAddress, 1);

					if(printToShell)
					{
						System.out.println("Barcode found at " + move.SrcType + ":" + move.SrcAddress);
					}

					move_list.get(i).SrcType = move.SrcType;
					move_list.get(i).SrcAddress = move.SrcAddress;
				}
			}
			
			if(move_list.get(i).SrcType.equalsIgnoreCase("slot") 
					|| move_list.get(i).SrcType.equalsIgnoreCase("drive"))
			{
				if(printToShell)
				{		
					System.out.print("Move " + i + ": " + move_list.get(i).SrcType
							+ ":" + move_list.get(i).SrcAddress + " > " 
							+ move_list.get(i).DestType + ":"
							+ move_list.get(i).DestAddress + "...\t");
				}
				
				response = library.moveMedia(ipaddress, port, move_list.get(i));

				if(printToShell)
				{
					System.out.println(response);
				}

			}
			else
			{
				logbook.logWithSizedLogRotation("Invalid move specified."
							+ move_list.get(i).SrcType + ":"
							+ move_list.get(i).SrcAddress + " > "
							+ move_list.get(i).DestType + ":"
							+ move_list.get(i).DestAddress, 3);
				
				if(printToShell)
				{
					System.out.println("Invalid Move Specified. " 
							+ move_list.get(i).SrcType + ":"
							+ move_list.get(i).SrcAddress + " > "
							+ move_list.get(i).DestType + ":"
							+ move_list.get(i).DestAddress);
				}
			}
		}
	}
}
