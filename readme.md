Kayongo Ivan
181511


This code is an evaluatioon of xPath and JAXB XJC. The XPath part prints values from an xml database of 50 people ie xpathDB.xml, it can print a person's details using his ID, and also their weight and height.
The second part ie JAXB XJC does the marshalling and un-marshalling of an XML file, and also creates a JSON database.


How to run it.
On the commandline termonal, change directory to the folder of the project ie "sde"

compile using ant; ie run

ant execute.evaluation


This will compile the project and download all the necessary dependences.

For XPath, run 

ant execute.HPReader

This prints three things ie
1. All the people in the people.xml database
2. The health profile of the person with ID 5
3. All the people with weight over 90 kilograms


For JAXB XJC, three commands are used ie

1. ant execute.JAXBMarshaller
	
	This creates an xml file called people.xml with 4 people and their details
	
2. ant execute.JAXBUnMarshaller
	
	This command gets details from the people.xml file created in (1) above and prints them
	
3. ant execute.HPJson
	
	The command creates a JSON database of 4 people
