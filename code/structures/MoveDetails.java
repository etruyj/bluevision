//===================================================================
//	MoveDetails
//		Holds the source and destination information for
//		moves passed to the library. Defined in order to
//		allow easier json encoding by Gson.
//===================================================================

package com.socialvagrancy.bluevision.structures;

public class MoveDetails
{
	public String SrcType = "none";
	public String SrcAddress = "none";
	public String DestType = "none";
	public String DestAddress = "none";
}
