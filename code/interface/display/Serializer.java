//===================================================================
// Serializer.java
//	Description:
//		Converts the different inherent structures to a simple
//		output format that can streamline coding the output code.
//===================================================================

package com.socialvagrancy.bluevision.ui.display;

import com.socialvagrancy.bluevision.structures.FormattedOutput;
import com.socialvagrancy.bluevision.structures.Inventory;
import com.socialvagrancy.bluevision.structures.MailSlotStatus;
import com.socialvagrancy.bluevision.structures.MediaInfo;
import com.socialvagrancy.bluevision.structures.PartitionInfo;

import java.util.ArrayList;

public class Serializer
{
	public static ArrayList<FormattedOutput> convert(Inventory inv)
	{
		ArrayList<FormattedOutput> output = new ArrayList<FormattedOutput>();
		FormattedOutput line;


		for(int i=0; i<inv.slotCount(); i++)
		{
			line = new FormattedOutput();
			line.header = "Inventory>Slot>PhysicalNumber";
			line.value = inv.Slots[i].PhysicalNumber;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot>LogicalNumber";
			line.value = inv.Slots[i].LogicalNumber;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot>Module";
			line.value = inv.Slots[i].Module;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot>Partition";
			line.value = inv.Slots[i].Partition;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot>Mailslot";
			line.value = inv.Slots[i].Mailslot;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot>Cartridge";
			line.value = inv.Slots[i].Cartridge;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot>Barcode";
			line.value = inv.Slots[i].Barcode;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot>CartridgeType";
			line.value = inv.Slots[i].CartridgeType;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot>CartridgeGeneration";
			line.value = inv.Slots[i].CartridgeGeneration;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot>CartridgeEncrypted";
			line.value = inv.Slots[i].CartridgeEncrypted;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot>Access";
			line.value = inv.Slots[i].Access;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot>Blocked";
			line.value = inv.Slots[i].Blocked;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Slot";
			line.value = null;
			output.add(line);
		}
		
		for(int i=0; i<inv.driveCount(); i++)
		{
			line = new FormattedOutput();
			line.header = "Inventory>Drive>PhysicalNumber";
			line.value = inv.Drives[i].PhysicalNumber;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Drive>LogicalNumber";
			line.value = inv.Drives[i].LogicalNumber;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Drive>Module";
			line.value = inv.Drives[i].Module;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Drive>Partition";
			line.value = inv.Drives[i].Partition;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Drive>Vendor";
			line.value = inv.Drives[i].Vendor;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Drive>Product";
			line.value = inv.Drives[i].Product;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Drive>FWRevision";
			line.value = inv.Drives[i].FWRevision;
			output.add(line);

			line = new FormattedOutput();
			line.header = "Inventory>Drive>SerialNumber";
			line.value = inv.Drives[i].SerialNumber;
			output.add(line);
			
			line = new FormattedOutput();
			line.header = "Inventory>Drive";
			line.value = null;
			output.add(line);
		}

		return output;
	}
	
	public static ArrayList<FormattedOutput> convert(MailSlotStatus[] mailslots)
	{
		ArrayList<FormattedOutput> output = new ArrayList<FormattedOutput>();
		FormattedOutput line;

		for(int i=0; i<mailslots.length; i++)
		{
		}

		return output;
	}
	
	public static ArrayList<FormattedOutput> convert(MediaInfo[] media)
	{
		ArrayList<FormattedOutput> output = new ArrayList<FormattedOutput>();
		FormattedOutput line;

		for(int i=0; i<media.length; i++)
		{
			line = new FormattedOutput();
			line.header = "MediaInfo>Tape>Barcode";
			line.value = media[i].Barcode;
			output.add(line);
		}

		return output;
	}
	
	public static ArrayList<FormattedOutput> convert(PartitionInfo[] pars)
	{
		ArrayList<FormattedOutput> output = new ArrayList<FormattedOutput>();
		FormattedOutput line;

		for(int i=0; i<pars.length; i++)
		{
			line = new FormattedOutput();
			line.header = "PartitionInfo>Partition>PartitionNumber";
			line.value = pars[i].PartitionNumber;
			output.add(line);

			line = new FormattedOutput();
			line.header = "PartitionInfo>Partition>Name";
			line.value = pars[i].Name;
			output.add(line);

			line = new FormattedOutput();
			line.header = "PartitionInfo>Partition>SerialNumber";
			line.value = pars[i].SerialNumber;
			output.add(line);

			line = new FormattedOutput();
			line.header = "PartitionInfo>Partition>NumSlots";
			line.value = pars[i].NumSlots;
			output.add(line);

			line = new FormattedOutput();
			line.header = "PartitionInfo>Partition>NumIOSlots";
			line.value = pars[i].NumIOSlots;
			output.add(line);

			line = new FormattedOutput();
			line.header = "PartitionInfo>Partition>NumDrives";
			line.value = pars[i].NumDrives;
			output.add(line);

			line = new FormattedOutput();
			line.header = "PartitionInfo>Partition>LunMasterDrive";
			line.value = pars[i].LunMasterDrive;
			output.add(line);

			line = new FormattedOutput();
			line.header = "PartitionInfo>Partition>LunMasterDrivePhys";
			line.value = pars[i].LunMasterDrivePhys;
			output.add(line);

			line = new FormattedOutput();
			line.header = "PartitionInfo>Partition>EncryptionMode";
			line.value = pars[i].EncryptionMode;
			output.add(line);
			
			line = new FormattedOutput();
			line.header = "PartitionInfo>Partition";
			line.value = null;
			output.add(line);

		}

		return output;
	}
}
