//===================================================================
// XML.java
// 	Description:
// 		The code to output to the screen in XML format.
//===================================================================

package com.socialvagrancy.bluevision.ui.display;

import com.socialvagrancy.bluevision.structures.FormattedOutput;
import java.util.ArrayList;

public class XML
{
	public static void print(ArrayList<FormattedOutput> output)
	{
		String[] headers; 
		String[] prev_headers = null;
		int base_level = 0;
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
					Shell.printIndents(base_level, j);

					if(j<headers.length-1)
					{
						// Print the header
						System.out.println("<" + headers[j] + ">");
					}
					else
					{
						// Print header value pair
						System.out.println("<" + headers[headers.length-1] + ">" + output.get(i).value + "</" + headers[headers.length-1] + ">");
					}
				}

				indent = headers.length - 1;
				last_header = headers[indent];

			}
			else if(headers.length-1 == indent && !(headers[indent].equals(last_header)))
			{

				Shell.printIndents(base_level, indent);
					
				System.out.println("<" + headers[indent] + ">" + output.get(i).value + "</" + headers[indent] + ">");
			
				

				last_header = headers[indent];
			}
			else if(output.get(i).value == null)
			{
				// Using the null var as a closing
				// brace.
				indent--;
				Shell.printIndents(base_level, indent);
				System.out.println("</" + headers[indent] + ">");
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
						Shell.printIndents(base_level, j);
						System.out.println("</" + prev_headers + ">");
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
					System.out.println("<" + headers[headers.length-1] + ">" + output.get(i).value + "</" + headers[headers.length-1] + ">");
				}
				else
				{
					for(int k=headers.length-1; k>=temp_indent; k++)
					{
						// Close out, down to the difference.
						Shell.printIndents(base_level, k);
						System.out.println("</" + prev_headers[k] + ">");
					}
					for(int k=temp_indent; k<headers.length-1; k++)
					{
						// Create new headers up to the change.
						Shell.printIndents(base_level, k);

						System.out.println("<" + headers[k] + ">");
					}
					
					System.out.println("<" + headers[headers.length-1] + ">" + output.get(i).value + "</" + headers[headers.length-1] + ">");
				}
			}
		}

		// Print the closing tags for the XML doc.
		// Start at -2 instead of -1 as the output.get(i).value==null listed above covers the original.
		headers = output.get(output.size()-1).header.split(">");

		for(int i=headers.length-2; i>=base_level; i--)
		{
			Shell.printIndents(base_level, i);
			System.out.println("</" + headers[i] + ">");
		}
	}
}
