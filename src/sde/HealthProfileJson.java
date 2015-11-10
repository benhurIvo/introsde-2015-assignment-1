package sde;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import sde.model.*;
import util.*;

public class HealthProfileJson {  	
static ArrayList<Person> peopleList = new ArrayList<Person>();
	public static void initializeDB() {
	
try {

	    //Array of names
	    ArrayList<String> nems = new ArrayList<String>();
	    nems.add("Roberto Rutter");
	    nems.add("Gwenda Garlick");
	    nems.add("Kareen Klapper");
	    nems.add("Izetta Iversen");
	    nems.add("Nicolette Neyman");
  
	    //Format to keep the IDs with leading zeros eg 0001
	    DecimalFormat decimalFormat = new DecimalFormat("0000");
	    
	    //Loop over the names and create a person, add to person list
	   for(int i=0;i<nems.size();i++){
		    String s = nems.get(i).trim();
		    Person persn = new Person();
		    persn.setPersonId(decimalFormat.format(i+1));
	 persn.setFirstname(s.substring(0,s.indexOf(" ")).trim());
    persn.setLastname(s.substring(s.indexOf(" ")).trim());
    persn.setBirthdate(DateGenerator.generateDate(1970, 1995));
    HealthProfile hp = new HealthProfile();
			hp.setLastupdate(DateGenerator.generateDate(2000, 2014));
			hp.setWeight(DoubleGenerator.getDoubleNumber(40.00,150.00,2));
			hp.setHeight(DoubleGenerator.getDoubleNumber(1.00,2.00,3));
			hp.setBMI(hp.getBMI());
    persn.setHProfile(hp);
    peopleList.add(persn);
	   }
	} catch (Exception e) {
			System.out.println(e.toString());

		} 
	}

	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        String result = mapper.writeValueAsString(peopleList);
        System.out.println(result);
        mapper.writeValue(new File("people.json"), peopleList);
    }
}
