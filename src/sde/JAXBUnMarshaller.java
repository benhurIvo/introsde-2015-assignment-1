package sde;

import sde.model.People;
import javax.xml.bind.*;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;

import org.xml.sax.SAXException;

import java.io.*;
import java.util.List;
import sde.model.Person;


public class JAXBUnMarshaller {
	public void unMarshall(File xmlDocument) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance("sde");

			Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = schemaFactory.newSchema(new File(
					"people.xsd"));
			unMarshaller.setSchema(schema);
			CustomValidationEventHandler validationEventHandler = new CustomValidationEventHandler();
			unMarshaller.setEventHandler(validationEventHandler);

			@SuppressWarnings("unchecked")
			JAXBElement<People> peopleElement = (JAXBElement<People>) unMarshaller
					.unmarshal(xmlDocument);

			People people = peopleElement.getValue();

			   System.out.println("People in the Database:\n");
			List<Person> personList = people.getPersonList();
			
			
			//loop over the people from the xml file
			for (int i = 0; i < personList.size(); i++) {
				Person person = (Person) personList.get(i);
				System.out.println("Person No: " +person.getPersonId());
				System.out.println("Firstname: " +person.getFirstname());
				System.out.println("Lastname : " +person.getLastname());
				System.out.println("Birthdate: " +person.getBirthdate());
				System.out.println("****Health Profile***** ");
				System.out.println("Lastupdate: " +person.getHProfile().getLastupdate());
				System.out.println("Weight: " +person.getHProfile().getWeight());
				System.out.println("Height: " +person.getHProfile().getHeight());
				System.out.println("BMI   : " +person.getHProfile().getBMI());
				   System.out.println("\n --------------------------------------------------------------\n");	
				
			}
		} catch (JAXBException e) {
			System.out.println(e.toString());
		} catch (SAXException e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String[] argv) {
	    //the xml file to unmarshal
		File xmlDocument = new File("people.xml");
		//Instance of the JAXBUnMarshaller class
		JAXBUnMarshaller jaxbUnmarshaller = new JAXBUnMarshaller();
//unmarshling
		jaxbUnmarshaller.unMarshall(xmlDocument);

	}

	class CustomValidationEventHandler implements ValidationEventHandler {
		public boolean handleEvent(ValidationEvent event) {
			if (event.getSeverity() == ValidationEvent.WARNING) {
				return true;
			}
			if ((event.getSeverity() == ValidationEvent.ERROR)
					|| (event.getSeverity() == ValidationEvent.FATAL_ERROR)) {

				System.out.println("Validation Error:" + event.getMessage());

				ValidationEventLocator locator = event.getLocator();
				System.out.println("at line number:" + locator.getLineNumber());
				System.out.println("Unmarshalling Terminated");
				return false;
			}
			return true;
		}

	}
}
