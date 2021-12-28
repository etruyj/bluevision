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

	public void trimWhiteSpace()
	{
		/*
		for(int s=0; s<slotCount(); s++)
		{
			Slots[s].PhysicalNumber = Slots[s].PhysicalNumber.trim();
			Slots[s].LogicalNumber = Slots[s].LogicalNumber.trim();
			Slots[s].Module = Slots[s].Module.trim();
			Slots[s].Partition = Slots[s].Partition.trim();
			Slots[s].Mailslot = Slots[s].Mailslot.trim();
			Slots[s].Cartridge = Slots[s].Cartridge.trim();
			
			if(Slots[s].Barcode != null)
			{
				Slots[s].Barcode = Slots[s].Barcode.trim();
			}
			
			Slots[s].CartridgeType = Slots[s].CartridgeType.trim();
			Slots[s].CartridgeGeneration = Slots[s].CartridgeGeneration.trim();
			Slots[s].CartridgeEncrypted = Slots[s].CartridgeEncrypted.trim();
			Slots[s].Access = Slots[s].Access.trim();

			if(Slots[s].Blocked != null)
			{
				Slots[s].Blocked = Slots[s].Blocked.trim();
			}
		}
		*/
		for(int d=0; d<driveCount(); d++)
		{
			Drives[d].PhysicalNumber = Drives[d].PhysicalNumber.trim();
			Drives[d].LogicalNumber = Drives[d].LogicalNumber.trim();
			Drives[d].Module = Drives[d].Module.trim();
			Drives[d].Partition = Drives[d].Partition.trim();
			Drives[d].Vendor = Drives[d].Vendor.trim();
			Drives[d].Product = Drives[d].Product.trim();
			Drives[d].FWRevision = Drives[d].FWRevision.trim();
			Drives[d].SerialNumber = Drives[d].SerialNumber.trim();
		}
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
