package com.socialvagrancy.bluevision.commands.sub;

import com.socialvagrancy.bluevision.structures.MoveDetails;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class MoveList
{
	public static MoveDetails convertToMoveDetails(String line)
	{
		// Going to set the desired file style to
		// Slot(#.#):Slot(#.#)
		String delimiter = ">";
		MoveDetails move = new MoveDetails();

		// Split the line into source and destination array.
		String[] moves = line.split(delimiter);
		String[] move_breakdown;

		// Verify there is only two
		if(moves.length == 2)
		{
			// Set Source
			move_breakdown = moves[0].split(":");
			move.SrcType = move_breakdown[0];
			move.SrcAddress = move_breakdown[1];
			// Set destination
			move_breakdown = moves[1].split(":");
			move.DestType = move_breakdown[0];
			move.DestAddress = move_breakdown[1];
		}
			
		return move;

	}

	public static ArrayList<MoveDetails> importMoves(String file_name)
	{

		ArrayList<MoveDetails> move_list = new ArrayList<MoveDetails>();

		try
		{
			File ifile = new File(file_name);

			BufferedReader br = new BufferedReader(new FileReader(ifile));
			
			String input = null;

			while((input=br.readLine()) != null)
			{
				if(!(input.equals("barcode") || input.equals("bar_code")))
				{
					move_list.add(convertToMoveDetails(input));
				}
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}

		return move_list;
	}
}
