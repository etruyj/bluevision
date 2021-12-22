package com.socialvagrancy.bluevision.ui;

import com.socialvagrancy.bluevision.structures.Inventory;
import com.socialvagrancy.bluevision.structures.MailSlotStatus;
import com.socialvagrancy.bluevision.structures.PartitionInfo;
import com.socialvagrancy.bluevision.utils.BlueController;

import java.util.ArrayList;

public class BlueVision
{
	BlueController controller;

	public BlueVision()
	{
		controller = new BlueController();
	}

	public void execute(String ip, String port, String command, String option1, String option2, String option3, String option4, boolean flag, String outputFormat)
	{
		String response;

		switch(command)
		{
			case "create-simple-partitions":
				controller.createSimplePartitions(ip, port, option1, option2, option3, flag);
				break;
			case "eject-listed-tapes":
			case "eject-listed":
			case "eject-list":
				controller.ejectTapes(ip, port, option1, option4, true);
				break;
			case "get-token":
				response = controller.getToken();
				Output.print(response);
				break;
			case "help":
				Output.printHelp("../lib/help/basic.txt");
				Output.printHelp("../lib/help/advanced.txt");
				break;
			case "help-basic":
				Output.printHelp("../lib/help/basic.txt");
				break;
			case "help-advanced":
				Output.printHelp("../lib/help/advanced.txt");
				break;
			case "list-inventory":
				Inventory inv = controller.listInventory(ip, port, option1);
				Output.print(inv, outputFormat);
				break;
			case "list-partitions":
				ArrayList<String> pars = controller.listPartitions(ip, port);
				Output.print(pars);
				break;
			case "mailslot-status":
				MailSlotStatus[] status = controller.mailslotStatus(ip, port);
				Output.print(status, outputFormat);
				break;
			case "move-media":
				response = controller.moveMedia(ip, port, option1, option2, option3, option4);
				Output.print(response);
				break;
			case "move-list":
				controller.moveList(ip, port, option4, option1);
				break;
			case "open-mailslots":
				response = controller.openMailslots(ip, port, option1);
				Output.print(response);
				break;
			case "partition-info":
				PartitionInfo[] partitions = controller.partitionInfo(ip, port);
				Output.print(partitions, outputFormat);
				break;
			case "partition-inventory":
				Inventory par_inv = controller.partitionInventory(ip, port, option1);
				Output.print(par_inv, outputFormat);
				break;
			case "protect-mailslots":
				controller.protectMailSlots(ip, port, option1, true);
				break;
			case "reboot":
				response = controller.rebootLibrary(ip, port);
				Output.print(response);
				break;
			case "scan-inventory":
				response = controller.inventoryScan(ip, port);
				Output.print(response);
				break;
			case "shutdown":
				response = controller.shutdownLibrary(ip, port);
				Output.print(response);
				break;
			case "default":
				Output.print("Invalid command selected. Please used -c help for a list of valid commands.");
				break;
		}
	}

	public boolean login(String ip, String port, String username, String password)
	{
		return controller.login(ip, port, username, password);
	}

	public static void main(String[] args)
	{
		ArgParser aparser = new ArgParser();

		if(aparser.parseArgs(args))
		{
			BlueVision ui = new BlueVision();
		
			if(aparser.helpRequested())
			{
				Output.printHelp("../lib/help/options.txt");
			}
			else if(aparser.getCommand().substring(0, 4).equals("help"))
			{
				ui.execute("", "",  aparser.getCommand(), "", "", "", "", false, "");
			}
			else if(ui.login(aparser.getIP(), aparser.getPort(), aparser.getUsername(), aparser.getPassword()))
			{
				ui.execute(aparser.getIP(), aparser.getPort(), aparser.getCommand(), aparser.getOption1(), aparser.getOption2(), aparser.getOption3(), aparser.getOption4(), aparser.getBooleanFlag(), aparser.getOutputFormat());
			}
			else
			{
				Output.print("Unable to login with specified credentials.");
			}
		}
		else
		{
			Output.print("Invalid input entered. Please use --help to see a list of valid commands.");
		}
	}
}

