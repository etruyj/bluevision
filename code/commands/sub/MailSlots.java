//===================================================================
// MailSlots.java
// 	This command is to located all the EE slots in the library.
//===================================================================

package com.socialvagrancy.bluevision.commands.sub;

import com.socialvagrancy.bluevision.commands.BasicCommands;
import com.socialvagrancy.bluevision.structures.Inventory;
import java.util.ArrayList;

public class MailSlots
{
	public static ArrayList<String> findAll(BasicCommands library, String ip, String port, String partition)
	{
		ArrayList<String> mailslots = new ArrayList<String>();

		Inventory inv = library.libraryInventory(ip, port);

		for(int i=0; i<inv.slotCount(); i++)
		{
			if(inv.Slots[i].Mailslot.equals("TRUE"))
			{
				mailslots.add(inv.Slots[i].LogicalNumber);
			}
		}

		return mailslots;
	}

	public static ArrayList<String> findEmpty(BasicCommands library, String ip, String port, String partition)
	{
		ArrayList<String> mailslots = new ArrayList<String>();

		Inventory inv = library.libraryInventory(ip, port);

		for(int i=0; i<inv.slotCount(); i++)
		{
			if(inv.Slots[i].Mailslot.equals("TRUE") && inv.Slots[i].Cartridge.equals("FALSE"))
			{
				mailslots.add(inv.Slots[i].LogicalNumber);
			}
		}

		return mailslots;
	}
}
