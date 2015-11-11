Kayongo Ivan
181511


This code is an evaluatioon of xPath and JAXB XJC. 
There are two folders under the src folder, "utils" and "sde", utils has java classes that have methods used to generate random values for use in other classes.
The "sde" folder the main classes which do all the work ie 
-XPath class which prints values from an xml database of 50 people ie xpathDB.xml, print a person's details using his ID, and also their weight and height.
-Marshalling and un-marshalling classes of an XML file, and also a class that creates a JSON database.
There is also an 'xsd' file which is used in the Marshalling and un-marshalling classes.

How to run it.
On the commandline terminal, change directory to the folder of the project ie "sde"

compile using ant; ie run

ant execute.evaluation

This will compile the project and download all the necessary dependences and then print the following

1. All the people in the people.xml database
2. The health profile of the person with ID 5
3. All the people with weight over 90 kilograms
4. Creates an xml file called people.xml with 4 people and their details
5. Gets details from the people.xml file created in (1) above and prints them
6. Creates a JSON database of 4 people
