/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sde;

import sde.model.People;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import sde.model.HealthProfile;
import sde.model.Person;
import util.*;

/**
 *
 * @author benhur
 */
public class JAXBMarshaller {

    private static final String PEOPLE_XML = "people.xml";

    public void generateXMLDocument() throws JAXBException {
	try {

	    ArrayList<Person> peopleList = new ArrayList<Person>();

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
		    persn.setFirstname(s.substring(0, s.indexOf(" ")).trim());
		    persn.setLastname(s.substring(s.indexOf(" ")).trim());
		    persn.setBirthdate(DateGenerator.generateDate(1970, 1995));
		    HealthProfile hp = new HealthProfile();
		    hp.setLastupdate(DateGenerator.generateDate(2000, 2014));
		    hp.setWeight(DoubleGenerator.getDoubleNumber(40.00, 150.00, 2));
		    hp.setHeight(DoubleGenerator.getDoubleNumber(1.00, 2.00, 3));
		    hp.setBMI(hp.getBMI());
		    persn.setHProfile(hp);

		    peopleList.add(persn);
		
	    }

	    // create People, assigning person
	    People people = new People();
	    people.setPeopleList(peopleList);

	    // create JAXB context and instantiate marshaller
	    JAXBContext context = JAXBContext.newInstance(People.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    // Write to System.out
	    m.marshal(people, System.out);

	    // Write to File
	    m.marshal(people, new File(PEOPLE_XML));

	} catch (Exception e) {
	    System.out.println(e.toString());

	}

    }

    public static void main(String[] args) throws JAXBException, IOException {
	JAXBMarshaller jaxbMarshaller = new JAXBMarshaller();
	jaxbMarshaller.generateXMLDocument();
    }
}
