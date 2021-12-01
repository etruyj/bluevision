//===================================================================
// Output.java
// 	Prints the output for the different scripts.
//===================================================================

package com.socialvagrancy.bluevision.ui;

import com.socialvagrancy.bluevision.structures.Inventory;
import com.socialvagrancy.bluevision.structures.PartitionInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class Output
{

	public static void print(ArrayList<String> response)
	{
		for(int i=0; i<response.size(); i++)
		{
			println("none", response.get(i), 0, "shell", false); 
		}
	}

	public static void print(String singleResponse)
	{
		// Print a single line response from a query
		// Used for failed queries and when there is no info.
		println("none", singleResponse, 0, "shell", false);
	}

	public static void print(Inventory inv, String outputFormat)
	{
		if(outputFormat.equals("shell"))
		{
			println("none", "", 0, outputFormat, false);
			println("Slots", "", 0, outputFormat, true);	
			// Print slots
			for(int i=0; i<inv.slotCount(); i++)
			{
				println("Physical Number", inv.Slots[i].PhysicalNumber, 1, outputFormat, true);
				println("Logical Number", inv.Slots[i].LogicalNumber, 1, outputFormat, true);
				println("Module", inv.Slots[i].Module, 1, outputFormat, true);
				println("Partition", inv.Slots[i].Partition, 1, outputFormat, true);
				println("EE Slot", inv.Slots[i].Mailslot, 1, outputFormat, true);
				println("Cartridge", inv.Slots[i].Cartridge, 1, outputFormat, true);
				println("Barcode", inv.Slots[i].Barcode, 1, outputFormat, true);
				println("Cartridge Type", inv.Slots[i].CartridgeType, 1, outputFormat, true);
				println("Cartridge Generation:", inv.Slots[i].CartridgeGeneration, 1, outputFormat, true);
				println("Cartridge Encrypted", inv.Slots[i].CartridgeEncrypted, 1, outputFormat, true);
				println("Access", inv.Slots[i].Access, 1, outputFormat, true);
				println("Blocked", inv.Slots[i].Blocked, 1, outputFormat, true);
				println("none", "", 0, outputFormat, false);
			}
			

			println("Drives", "", 0, outputFormat, true);
			// Print Drives
			for(int i=0; i<inv.driveCount(); i++)
			{
				println("Physical Number", inv.Drives[i].PhysicalNumber, 1, outputFormat, true);
				println("Logical Number", inv.Drives[i].LogicalNumber, 1, outputFormat, true);
				println("Module", inv.Drives[i].Module, 1, outputFormat, true);
				println("Partition", inv.Drives[i].Partition, 1, outputFormat, true);
				println("Barcode", inv.Drives[i].Barcode, 1, outputFormat, true);
				println("Vendor", inv.Drives[i].Vendor, 1, outputFormat, true);
				println("Product", inv.Drives[i].Product, 1, outputFormat, true);
				println("FW Revision", inv.Drives[i].FWRevision, 1, outputFormat, true);
				println("Serial Number", inv.Drives[i].SerialNumber, 1, outputFormat, true);		
				println("none", "", 0, outputFormat, false);
			}
		}
		else
		{
			println("Slots", "", 0, outputFormat, true);
			// Print slots
			for(int i=0; i<inv.slotCount(); i++)
			{
				println("PhysicalNumber", inv.Slots[i].PhysicalNumber, 1, outputFormat, true);
				println("LogicalNumber", inv.Slots[i].LogicalNumber, 1, outputFormat, true);
				println("Module", inv.Slots[i].Module, 1, outputFormat, true);
				println("Partition", inv.Slots[i].Partition, 1, outputFormat, true);
				println("EESlot", inv.Slots[i].Mailslot, 1, outputFormat, true);
				println("Cartridge", inv.Slots[i].Cartridge, 1, outputFormat, true);
				println("Barcode", inv.Slots[i].Barcode, 1, outputFormat, true);
				println("CartridgeType", inv.Slots[i].CartridgeType, 1, outputFormat, true);
				println("CartridgeGeneration:", inv.Slots[i].CartridgeGeneration, 1, outputFormat, true);
				println("Cartridge Encrypted", inv.Slots[i].CartridgeEncrypted, 1, outputFormat, true);
				println("Access", inv.Slots[i].Access, 1, outputFormat, true);
				println("Blocked", inv.Slots[i].Blocked, 1, outputFormat, true);
			}
			
			println("Drives", "", 0, outputFormat, true);
			// Print Drives
			for(int i=0; i<inv.driveCount(); i++)
			{
				println("PhysicalNumber", inv.Drives[i].PhysicalNumber, 1, outputFormat, true);
				println("LogicalNumber", inv.Drives[i].LogicalNumber, 1, outputFormat, true);
				println("Module", inv.Drives[i].Module, 1, outputFormat, true);
				println("Partition", inv.Drives[i].Partition, 1, outputFormat, true);
				println("Barcode", inv.Drives[i].Barcode, 1, outputFormat, true);
				println("Vendor", inv.Drives[i].Vendor, 1, outputFormat, true);
				println("Product", inv.Drives[i].Product, 1, outputFormat, true);
				println("FWRevision", inv.Drives[i].FWRevision, 1, outputFormat, true);
				println("SerialNumber", inv.Drives[i].SerialNumber, 1, outputFormat, true);		
			}
		}
	}

	public static void print(PartitionInfo[] partitions, String outputFormat)
	{
		// Print the partition info.
		for(int i=0; i<partitions.length; i++)
		{
			if(outputFormat.equals("shell"))
			{
				println("none", "", 0, outputFormat, false);
				println("Partition Number", partitions[i].PartitionNumber, 0, outputFormat, true);
				println("Name", partitions[i].Name, 0, outputFormat, true);
				println("Serial Number", partitions[i].SerialNumber, 0, outputFormat, true);
				println("Storage Slots", partitions[i].SerialNumber, 0, outputFormat, true);
				println("EE Slots", partitions[i].NumIOSlots, 0, outputFormat, true);
				println("Drives", partitions[i].NumDrives, 0, outputFormat, true);
				println("LUN Master Drive", partitions[i].LunMasterDrive, 0, outputFormat, true);
				println("LUN Drive Position", partitions[i].LunMasterDrivePhys, 0, outputFormat, true);
				println("Encryption Mode", partitions[i].EncryptionMode, 0, outputFormat, true);
			}
			else
			{
				println("PartitionNumber", partitions[i].PartitionNumber, 0, outputFormat, true);
				println("Name", partitions[i].Name, 0, outputFormat, true);
				println("SerialNumber", partitions[i].SerialNumber, 0, outputFormat, true);
				println("NumSlots", partitions[i].SerialNumber, 0, outputFormat, true);
				println("NumIOSlots", partitions[i].NumIOSlots, 0, outputFormat, true);
				println("NumDrives", partitions[i].NumDrives, 0, outputFormat, true);
				println("LunMasterDrive", partitions[i].LunMasterDrive, 0, outputFormat, true);
				println("LunMasterDrivePhys", partitions[i].LunMasterDrivePhys, 0, outputFormat, true);
				println("EncryptionMode", partitions[i].EncryptionMode, 0, outputFormat, true);
			}
		}
	}

	//==============================================
	//	PRINT FUNCTIONS
	//==============================================

	public static void printHelp(String file_name)
	{
		try
		{
			File ifile = new File(file_name);

			BufferedReader br = new BufferedReader(new FileReader(ifile));

			String line = null;

			while((line = br.readLine()) !=null)
			{
				System.err.println(line);
			}

			System.err.print("\n");
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
	}

	public static void println(String header, String value, int indent, String outputFormat, boolean includeHeaders)
	{
		if(outputFormat.equals("shell"))
		{
			printShell(header, value, indent, includeHeaders);
		}
		else if(outputFormat.equalsIgnoreCase("XML"))
		{
		}
		else if(outputFormat.equalsIgnoreCase("json"))
		{
		}
	}

	public static void printShell(String header, String value, int indent, boolean includeHeaders)
	{
		for(int i=0; i<indent; i++)
		{
			System.out.print("\t");
		}

		if(includeHeaders)
		{
			System.out.print(header + ": ");
		}

		System.out.println(value);
	}
}
