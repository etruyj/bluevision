//===================================================================
// Display.java
// 	Description:
// 		The code to output to the screen.
//===================================================================

package com.socialvagrancy.bluevision.ui.display;

import com.socialvagrancy.bluevision.structures.FormattedOutput;
import com.socialvagrancy.bluevision.structures.Inventory;
import com.socialvagrancy.bluevision.structures.MailSlotStatus;
import com.socialvagrancy.bluevision.structures.MediaInfo;
import com.socialvagrancy.bluevision.structures.PartitionInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Display
{
	//=======================================
	// Input Functions
	//=======================================

	public static void output(Inventory inv, String output_format)
	{
		if(output_format.equals("json"))
		{
			// Use Gson for the conversion
			// and print immediately to screen.
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(inv));
		}
		else
		{
			ArrayList<FormattedOutput> output = Serializer.convert(inv);
		
			if(output_format.equals("xml"))
			{
				XML.print(output);
			}
			else if(output_format.equals("shell"))
			{
				Shell.print(output);
			}
		}
	}

	public static void output(MailSlotStatus[] mailslots, String output_format)
	{
		if(output_format.equals("json"))
		{
			// Use Gson for the conversion
			// and print immediately to screen.
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(mailslots));
		}
		else
		{
			ArrayList<FormattedOutput> output = Serializer.convert(mailslots);
		
			if(output_format.equals("xml"))
			{
				XML.print(output);
			}
			else if(output_format.equals("shell"))
			{
				Shell.print(output);
			}
		}
	}

	public static void output(MediaInfo[] media, String output_format)
	{
		if(output_format.equals("json"))
		{
			// Use Gson for the conversion
			// and print immediately to screen.
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(media));
		}
		else
		{
			ArrayList<FormattedOutput> output = Serializer.convert(media);
		
			if(output_format.equals("xml"))
			{
				XML.print(output);
			}
			else if(output_format.equals("shell"))
			{
				Shell.print(output);
			}
		}
	}

	public static void output(PartitionInfo[] pars, String output_format)
	{
		if(output_format.equals("json"))
		{
			// Use Gson for the conversion
			// and print immediately to screen.
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(pars));
		}
		else
		{
			ArrayList<FormattedOutput> output = Serializer.convert(pars);
		
			if(output_format.equals("xml"))
			{
				XML.print(output);
			}
			else if(output_format.equals("shell"))
			{
				Shell.print(output);
			}
		}
	}
}
