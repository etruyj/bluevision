//===================================================================
// MediaInfo.java
// 	Holds media information for tapes.
//
// 	Used in:
// 	- 2.8.3 - List of media of a partition
//===================================================================

package com.socialvagrancy.bluevision.structures;

public class MediaInfo
{
	public String Barcode;
	public String LocationType;
	public String LogicalNumber;
	public String PhysicalNumber;
	public boolean Cleaning;
	public String Partition;
	public String Generation;
	public boolean Protection;
	public boolean Encryption;
	public String NoLoads;
	public String MBRead;
	public String MBReadLoad;
	public String MBWritten;
	public String MBWrittenLoad;
}
