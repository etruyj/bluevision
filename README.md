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

### Commands

### Input Files
#### Eject Files
#### Move Files

## Errors
blue_api stores logs in the log/ sub-directory for the program. The name of the file is bluevision.log
