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

		int src = 0;
		int dest = 0;
		String[] slot;

		while((src < source.size()) && (dest < mailslots.size()))
		{
			move = new MoveDetails();
			
			slot = source.get(src).split(":");
			source.remove(0);

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
			else if(!slot[0].equals("ERROR"))
			{
				// (Hopefully) a barcode was entered.
				move.SrcType = "barcode";
				move.SrcAddress = slot[1];
				move.DestType = "Slot";
				move.DestAddress = mailslots.get(dest);
				dest++;	
			}
			
			move_list.add(move);

			src++;
		}

		// Cheating on my reporting...
		move = new MoveDetails();
		
		if(src >= source.size())
		{
			move.SrcType = "All";
			move.SrcAddress = Integer.toString(src);
			move.DestAddress = Integer.toString(dest);
		}
		else
		{
			move.DestType = "All";
			move.SrcAddress = Integer.toString(src);
			move.DestAddress = Integer.toString(dest);
		}
		
		move_list.add(move);

		exportRemaining(source, file_name);

		return move_list; 
	}

	public static void exportRemaining(ArrayList<String> source, String file_name)
	{
		if(source.size()>0)
		{
			FileManager fman = new FileManager();
			fman.createFileDeleteOld(file_name, true);

			for(int i=0; i<source.size(); i++)
			{
				fman.appendToFile(file_name, source.get(i));
			}
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
				System.err.println("import line: " + line);
				source.add(line);
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			source.add("ERROR: " + e.getMessage());
		}
		
		System.err.println("tapes imported: " + source.size());

		return source;
	}
}
