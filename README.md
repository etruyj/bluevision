# BlueVision blue_api

## Description
blue_api is a command line script for both linux and Windows (.bat) environments to allow automated administration a Spectra Logic Stack tape library. This is a work in progress that is being expanded as needed. Current it only allows for automating tape moves and tape ejection along with the API calls necessary for these commands to work. If any features are desired, please log them in the Issues as enhancement requests.

## Using
blue_api is a command line tool. After decompressing the tar or zip file, nagivate to the bin directory to find the blue_api shell or batch script. Windows users will use the batch script. Linux/Mac OS users will use the shell script to execute commands.

### Samples
The following samples demonstrate a few of the commands for blue_api. Capitalized words represent variable values that should be edited to reflect the execution environment. For example, IP_ADDRESS must be replaced with the IP address of the Spectra Stack library, such as 10.10.10.15. 

Some of the flags can be abbreviated to their initial letter. This can be seen in the below examples where both --command and -c work, --endpoint and -e work, and --username and -u work for parsing the variable.

#### Get a list of partition names
`./blue_api --endpoint IP_ADDRESS --username USERNAME --password PASSWORD --command list-partitions`

#### List inventory
`./blue_api --endpoint IP_ADDRESS --username USERNAME --password PASSWORD -c list-inventory`

#### Move a tape to a drive
`./blue_api -e IP_ADDRESS -u USERNAME -p PASSWORD -c move-media --source-type slot --source 1.1 --target-type drive --target 1`

#### Eject tapes contained in a specified list
`./blue_api -e IP_ADDRESS -u USERNAME -p PASSWORD -c eject-list --intput-file tapes.csv`

### Options
--command(-c)	  The command to be executed. Commands can be listed with -c help, -c help-basic, or -c help-advanced  
--endpoint(-e)	IP Address of the Stack  
--input-file	  File to be read from  
--password(-p)  Password  
--port		      The port the API is listening to if a non-standard port.  
--source	      The source address for a specified move, e.g. 1.1  
--source-type	  The type of source being specified for a move, e.g. drive or slot.  
--target	      The target address for a specified move, eg. 1.71  
--target-type	  The type of target being speficied for a move, e.g. drive or slot.  
--username(-u)	Username  

### Commands
eject-list	    Ejects all the tapes or slots specified in an eject list file. Requires --input-file  
list-inventory	List information on all slots and drives in the library.  
list-partitions	Prints all partition names.  
move-list	      Executes the moves specified in a move list file. Requires --input-file  
move-media	    Sends a move command to the libary. Must specify --source-type, --source, --target-type, and --target  
partition-info	Lists information on all partitions in the library.  

### Input Files
Some commands accept an input file (specified with the --input-file flag) in order to push information to the script. This section will explain how to configure those commands. With the exception of newlines, do not enter white space in these files.

#### Eject Files
Eject files are a list of tapes or slots to be ejected. Options can either be a slot, a drive, or a specific tape. Each option should be on its own line. A colon (:) must be used to separate the source type (slot, drive, barcode or bc) and the source value (address or barcode) to be ejected. For tape barcodes, only the barcode can be specified. In the case of a single barcode, no source type needs to be specified. The eject-list command searches for available 

##### Sample File
barcode:000001L8
bc:000002L8
000003L8
slot:1.5

#### Move Files
Move files are a list of tape moves.
#### Sample File
## Errors
blue_api stores logs in the log/ sub-directory for the program. The name of the file is bluevision.log
