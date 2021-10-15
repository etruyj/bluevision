//===================================================================
// Inventory.java
// 	This structure holds the information returned in the 
// 	/partition/inventory api call.
//===================================================================

package com.socialvagrancy.bluevision.structures;

public class Inventory
{
	public Slot[] Slots;
	public Drive[] Drives;

	public int slotCount() { return Slots.length; }
	public int driveCount() { return Drives.length; }

	public String barcodeAtSlot(String slot)
	{
		for(int i=0; i<slotCount(); i++)
		{
			if(Slots[i].LogicalNumber.equals(slot))
			{
				return Slots[i].Barcode;
			}
		}

		return "none";
	}

	public String slotForBarcode(String barcode)
	{
		for(int i=0; i<slotCount(); i++)
		{
			if(Slots[i].Barcode.equalsIgnoreCase(barcode))
			{
				return Slots[i].LogicalNumber;
			}
		}

		return "not found";
	}

	public String barcodeInDrive(String number)
	{
		for(int i=0; i<driveCount(); i++)
		{
			if(Drives[i].LogicalNumber.equals(number))
			{
				return Drives[i].Barcode;
			}
		}

		return "none";
	}

	public String driveForBarcode(String barcode)
	{
		for(int i=0; i<driveCount(); i++)
		{
			if(Drives[i].Barcode.equalsIgnoreCase(barcode))
			{
				return Drives[i].LogicalNumber;
			}
		}

		return "none";
	}

	public class Slot
	{
		public String PhysicalNumber;
		public String LogicalNumber;
		public String Module;
		public String Partition;
		public String Mailslot;
		public String Cartridge;
		public String Barcode;
		public String CartridgeType;
		public String CartridgeGeneration;
		public String CartridgeEncrypted;
		public String Access;
		public String Blocked;
	}

	public class Drive
	{
		public String PhysicalNumber;
		public String LogicalNumber;
		public String Module;
		public String Partition;
		public String Barcode;
		public String Vendor;
		public String Product;
		public String FWRevision;
		public String SerialNumber;
	}
}
