package sde.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import sde.model.Person;

//This statement means that class "People.java" is the root-element of our example
@XmlRootElement
public class People {

  // XmlElement sets the name of the entities
  @XmlElement(name = "person")
  private ArrayList<Person> personList;

  public void setPeopleList(ArrayList<Person> personList) {
    this.personList = personList;
  }

  public ArrayList<Person> getPersonList() {
    return personList;
  }

} 