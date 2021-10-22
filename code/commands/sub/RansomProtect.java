//===================================================================
// RansomProtect.java
// 	This command is used to move move specified tapes from the
// 	entry/exit slots to unpartitioned space on the library so
// 	they are not accessbile from the backup software.
//===================================================================

package com.socialvagrancy.bluevision.commands.sub;

import com.socialvagrancy.bluevision.commands.BasicCommands;
import com.socialvagrancy.bluevision.structures.Inventory;
import com.socialvagrancy.bluevision.structures.MoveDetails;
import com.socialvagrancy.utils.Logger;
import java.util.ArrayList;

public class RansomProtect
{
	public static ArrayList<MoveDetails> fromMailSlots(ArrayList<MoveDetails> mail_slots, ArrayList<String> target_slots)
	{
		int itr = 0;

		// Assign destination address to the slots.
		while(itr < mail_slots.size() && itr < target_slots.size())
		{
			mail_slots.get(itr).DestType = "Slot";
			mail_slots.get(itr).DestAddress = target_slots.get(itr);
			
			itr++;
		}

		// Remove unassigned slots
		for(int i=itr; i<mail_slots.size(); i++)
		{
			mail_slots.remove(itr);
		}
			
		return mail_slots;
	}

	public static ArrayList<String> findFreePoolSlots(Inventory inv)
	{
		ArrayList<String> freepool_slots = new ArrayList<String>();

		for(int i=0; i<inv.slotCount(); i++)
		{
			if(inv.Slots[i].Partition.equals("0") && inv.Slots[i].Cartridge.equals("FALSE"))
			{
				freepool_slots.add(inv.Slots[i].LogicalNumber);
			}
		}

		return freepool_slots;
	}

	public static ArrayList<MoveDetails> findMailSlotTapes(Inventory inv)
	{
		ArrayList<MoveDetails> mail_slots = new ArrayList<MoveDetails>();
		MoveDetails move;

		for(int i=0; i<inv.slotCount(); i++)
		{
			if(inv.Slots[i].Mailslot.equals("TRUE") && inv.Slots[i].Cartridge.equals("TRUE"))
			{
				move = new MoveDetails();
				move.SrcType = "Slot";
				move.SrcAddress = inv.Slots[i].LogicalNumber;
				move.DestAddress = inv.Slots[i].Barcode;

				mail_slots.add(move);
			}
		}

		return mail_slots;
	}

	public static void updateLogs(ArrayList<MoveDetails> mail_slots, ArrayList<String> freepool, Logger logbook)
	{
		int itr = 0;

		if(mail_slots.size() > freepool.size())
		{
			logbook.logWithSizedLogRotation("There are " + freepool.size() + " slots available in the freepool.", 2);
			logbook.logWithSizedLogRotation("There are " + mail_slots.size() + " tapes in mail slots.", 2);
			logbook.logWithSizedLogRotation(mail_slots.size() - freepool.size() + " tapes will remain.", 2);
		}

		if(mail_slots.size()==0)
		{
			logbook.logWithSizedLogRotation("No tapes in mail slots. No moves to be completed.", 2);	
	       	}

		if(freepool.size()==0)
		{
			logbook.logWithSizedLogRotation("No slots available in freepool. No moves to be completed.", 2);
		}

		while(itr < mail_slots.size() && itr < freepool.size())
		{
			logbook.logWithSizedLogRotation("Queueing move (" + mail_slots.get(itr).DestAddress + ") " 
					+ mail_slots.get(itr).SrcType + ":" + mail_slots.get(itr).SrcAddress 
					+ " to Slot:" + freepool.get(itr), 2);

			itr++;
		}
	}
}
