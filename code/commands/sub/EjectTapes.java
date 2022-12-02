package com.socialvagrancy.bluevision.commands.sub;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import com.socialvagrancy.bluevision.structures.Inventory;
import com.socialvagrancy.bluevision.structures.MoveDetails;

import com.socialvagrancy.utils.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class EjectTapes
{
	public static ArrayList<MoveDetails> prepareMoves(String file_name, ArrayList<String> mailslots)
	{
		ArrayList<String> source = importList(file_name);
		ArrayList<MoveDetails> move_list = new ArrayList<MoveDetails>();
		MoveDetails move;

		int list_size = source.size();
		int tapes_queued = 0;
		int dest = 0;
		String[] slot;

		while((source.size() > 0) && (dest < mailslots.size()))
		{
			move = new MoveDetails();
			
			slot = source.get(0).split(":");

			// Convert lines imported from the file
			// to move format.
			//
			// Allowed inputs:
			// 	- barcode:000001L8
			// 	- bc:000001L8
			// 	- 000001L8
			// 	- slot:1.5
			// 
			// Split on : to see if just the barcode was specified.

			if(slot[0].equalsIgnoreCase("slot"))
			{
				// Slot was provided.
				
				move.SrcType = "Slot";
				move.SrcAddress = slot[1];
				move.DestType = "Slot";
				move.DestAddress = mailslots.get(dest);
				dest++;	
			}
			else if(slot.length==1)
			{
				// Only a barcode appears in the list.
				move.SrcType = slot[0];
				move.SrcAddress = "none";
				move.DestType = "slot";
				move.DestAddress = mailslots.get(dest);
				dest++;
			}
			else if(slot[0].equals("barcode") || slot[0].equals("bc"))
			{
				// Test for either valid barcode input.
				move.SrcType = "barcode";
				move.SrcAddress = slot[1];
				move.DestType = "Slot";
				move.DestAddress = mailslots.get(dest);
				dest++;
			}
			else
			{
				// Move is invalid.
				System.err.println("ERROR: Invalid move specified: " + source.get(0));
				move = null;
			}
			/*
			 * Mark for deletion on v1.2.2
			 * 	Not sure why this code was here.
			 * 	
			else if(!slot[0].equals("ERROR"))
			{
				// (Hopefully) a barcode was entered.
				move.SrcType = "barcode";
				move.SrcAddress = slot[1];
				move.DestType = "Slot";
				move.DestAddress = mailslots.get(dest);
				dest++;	
			}
			*/


			if(move != null)
			{
				move_list.add(move);
				tapes_queued++;
			}
		
			// Instead of incrementing the src counter, remove 0 reference.
			// This allows us to accurately rebuild the move queue with moves
			// that weren't execute.	
			source.remove(0);
		}

		// Storage reporting - Were all listed moves queued?
		// 	Create an entry at the end of the move queue stating
		// 	all the listed tapes have been queued. As we're reporting
		// 	in a different function (command/AdvancedCommands.java),
		// 	there's no way to tell if all tapes were moved at that level.
		// 	This is a bit of a cheat.
		move = new MoveDetails();
		
		if(tapes_queued >= list_size)
		{
			move.SrcType = "All";
			move.SrcAddress = Integer.toString(tapes_queued);
			move.DestAddress = Integer.toString((mailslots.size() - dest));
		}
		else
		{
			move.DestType = "All";
			move.SrcAddress = Integer.toString(tapes_queued);
			move.DestAddress = Integer.toString((mailslots.size() - dest));
		}
		
		move_list.add(move);

		exportRemaining(source, file_name);

		return move_list; 
	}

	public static void exportRemaining(ArrayList<String> source, String file_name)
	{
		
		if(source.size()>0)
		{
			System.err.println("Writing remaining (" + source.size() + ") moves back to " + file_name);
			FileManager fman = new FileManager();
			fman.createFileDeleteOld(file_name, true);

			for(int i=0; i<source.size(); i++)
			{
				fman.appendToFile(file_name, source.get(i));
			}
		}
		else
		{
			// All sources are used.
			System.err.println("No moves remain. Deleting file.");
			new File(file_name).delete();
		}
	}

	public static ArrayList<String> importList(String file_name)
	{
		ArrayList<String> source = new ArrayList<String>();
		

		try
		{
			File ifile = new File(file_name);

			BufferedReader br = new BufferedReader(new FileReader(ifile));

			String line = null;
			
			while((line = br.readLine()) != null)
			{
				source.add(line);
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			source.add("ERROR: " + e.getMessage());
		}
		
		return source;
	}
}
