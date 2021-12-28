//===================================================================
// Shell.java
// 	Description:
// 		The code to output to the screen in shell format.
//===================================================================

package com.socialvagrancy.bluevision.ui.display;

import com.socialvagrancy.bluevision.structures.FormattedOutput;
import java.util.ArrayList;

public class Shell
{
	public static void print(ArrayList<FormattedOutput> output)
	{
		String[] headers; 
		String[] prev_headers = null;
		int base_level = 1;
		int indent = base_level; // Start at 1 to skip the sheet cover.
		String last_header="none";

		for(int i=0; i<output.size(); i++)
		{
			headers = output.get(i).header.split(">");

			// Check to see if we're at the right document level.
			// Is the value deeper than the last value.
			if(headers.length - 1 > indent)
			{
				for(int j=indent; j<headers.length; j++)
				{
					printIndents(base_level, j);

					if(j<headers.length-1)
					{
						// Print the header
						System.out.println(headers[j]);
					}
					else
					{
						// Print header value pair
						System.out.println(headers[headers.length-1] + ": " + output.get(i).value);
					}
				}

				indent = headers.length - 1;
				last_header = headers[indent];

			}
			else if(headers.length-1 == indent && !(headers[indent].equals(last_header)))
			{
				prev_headers = output.get(i-1).header.split(">");

				for(int j=base_level; j<headers.length; j++)
				{
					if(!prev_headers[j].equals(headers[j]))
					{
						for(int k=base_level; k<indent; k++)
						{
							System.out.print("\t");
						
							if(indent < headers.length-1)
							{
								System.out.println(headers[j]);
							}
							else
							{
								System.out.println(headers[j] + ": " + output.get(i).value);
							}
						}
					}
				}

				last_header = headers[indent];
			}
			else if(output.get(i).value == null)
			{
				// Using the null var as a closing
				// brace. This will just output the
				// a newline.
				System.out.print("\n");
				indent--;
			}
			else if(headers.length < indent)
			{
				prev_headers = output.get(i-1).header.split(">");
				int temp_indent = indent;

				for(int j=indent; j>=0; j--)
				{
					// Find the change in the doc.
					if(j > headers.length)
					{
						// For regular shell output,
						// do nothing.
						indent--;
					}
					else
					{
						if(!prev_headers[j].equals(headers[j]))
						{
							temp_indent = j;
						}
					}
				}

				if(temp_indent == headers.length)
				{
					System.out.println(headers[headers.length-1] + ": " + output.get(i).value);
				}
				else
				{
					for(int k=temp_indent; k<headers.length-1; k++)
					{
						printIndents(base_level, k);

						System.out.println(headers[k]);
					}
					
					System.out.println(headers[headers.length-1] + ": " + output.get(i).value);
				}
			}
		}
	}
	
	//======================================
	// Formatting Functions
	//	Repeated functions in the display
	//	code used to format the output.
	//======================================
	
	public static void printIndents(int doc_base_level, int indent)
	{
		for(int i = doc_base_level; i < indent; i++)
		{
			System.out.print("\t");
		}
	}	

}
