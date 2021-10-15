package com.socialvagrancy.bluevision.commands.sub;

import com.socialvagrancy.bluevision.structures.Inventory;
import com.socialvagrancy.bluevision.structures.MoveDetails;

public class BarcodeSlot
{
	public static String barcodeAtSlot(Inventory inv, String slot)
	{
		for(int i=0; i<inv.slotCount(); i++)
		{
			if(inv.Slots[i].LogicalNumber.equals(slot))
			{
				return inv.Slots[i].Barcode;
			}
		}

		return "none";
	}

	public static String slotForBarcode(Inventory inv, String barcode)
	{
		for(int i=0; i<inv.slotCount(); i++)
		{
			if(inv.Slots[i].Cartridge.equals("TRUE") && inv.Slots[i].Barcode.equalsIgnoreCase(barcode))
			{
				return inv.Slots[i].LogicalNumber;
			}
		}

		return "none";
	}

	public static String barcodeInDrive(Inventory inv, String number)
	{
		for(int i=0; i<inv.driveCount(); i++)
		{
			if(inv.Drives[i].LogicalNumber.equals(number))
			{
				return inv.Drives[i].Barcode;
			}
		}

		return "none";
	}

	public static String driveForBarcode(Inventory inv, String barcode)
	{
		for(int i=0; i<inv.driveCount(); i++)
		{
			if(inv.Drives[i].Barcode != null && inv.Drives[i].Barcode.equalsIgnoreCase(barcode))
			{
				return inv.Drives[i].LogicalNumber;
			}
		}

		return "none";
	}

	public static MoveDetails findSlot(Inventory inv, MoveDetails move)
	{
		String slot = slotForBarcode(inv, move.SrcAddress);

		if(!slot.equals("none"))
		{
			move.SrcType = "Slot";
			move.SrcAddress = slot;
		}
		else
		{
			slot = driveForBarcode(inv, move.SrcAddress);

			if(!slot.equals("none"))
			{
				move.SrcType = "Drive";
				move.SrcAddress = slot;
			}
			else
			{
				// not found in inventory
				move.SrcType = "none";
				move.SrcAddress = "none";
			}
		}

		return move;
	}
}
