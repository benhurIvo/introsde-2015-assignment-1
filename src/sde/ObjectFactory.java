//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.09 at 02:07:04 PM EAT 
//


package sde;

import sde.model.People;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import sde.model.Person;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bookstore.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Person_QNAME = new QName("", "person");
    private final static QName _People_QNAME = new QName("", "people");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bookstore.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createJournalType() {
        return new Person();
    }

   
    /**
     * Create an instance of {@link CatalogType }
     * 
     */
    public People createCatalogType() {
        return new People();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JournalType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "journal")
    public JAXBElement<Person> createJournal(Person value) {
        return new JAXBElement<Person>(_Person_QNAME, Person.class, null, value);
    }

      /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CatalogType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "people")
    public JAXBElement<People> createCatalog(People value) {
        return new JAXBElement<People>(_People_QNAME, People.class, null, value);
    }

}
