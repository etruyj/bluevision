//===================================================================
// Partition.java
//	Partition related commands such as searching for a partition
//	number or filtering results by partitin.
//===================================================================

package com.socialvagrancy.bluevision.commands.sub;

import com.socialvagrancy.bluevision.structures.PartitionInfo;

import java.util.ArrayList;

public class Partition
{
	public static String getPartitionNumber(String partition, PartitionInfo[] partitions)
	{
		try
		{
			int test = Integer.valueOf(partition);
			return partition;
		}	
		catch(Exception e)
		{
			for(int i=0; i<partitions.length; i++)
			{
				if(partitions[i].Name.equals(partition))
				{
					return partitions[i].PartitionNumber;
				}
			}

			return "-1";
		}
	}
}

