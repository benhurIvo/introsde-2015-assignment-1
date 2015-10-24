package sde;
/**
 * Based on code made by Muhammad Imran
 */
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathPrinter {

    Document doc;
    XPath xpath;
    DocumentBuilder builder;
    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	    
    public void loadXML() throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        builder = domFactory.newDocumentBuilder();
        doc = builder.parse("xpathDB.xml");

        //creating xpath object
        getXPathObj();
    }

    public XPath getXPathObj() {

        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
        return xpath;
    }

    // Get Weight of person with that id
    public Node getWeight(long id) throws XPathExpressionException {

        XPathExpression expr = xpath.compile("/people/person[@id ="+id+"]/healthprofile/weight");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }

    // Get Height of person with that id
public Node getHeight(long id) throws XPathExpressionException {

        XPathExpression expr = xpath.compile("/people/person[@id ="+id+"]/healthprofile/height");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }

// Print all the people in the database
  public NodeList printPeople() throws XPathExpressionException,SAXException,IOException,ParserConfigurationException {

      XPathExpression expr = xpath.compile("/people");
        doc = builder.parse("xpathDB.xml");

	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	        System.out.println(nodes.item(i).getTextContent());
	    }
	    return null;
    }
  
  // Pring health profile of person with given ID
  public NodeList printHealthProfile(long id) throws XPathExpressionException,SAXException,IOException,ParserConfigurationException {

      XPathExpression expr = xpath.compile("/people/person[@id ="+id+"]");
        doc = builder.parse("xpathDB.xml");
	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	        System.out.println(nodes.item(i).getTextContent());
	    }
	    return null;
    }
  
  public NodeList calcWeight(String wt) throws XPathExpressionException,SAXException,IOException,ParserConfigurationException {

      XPathExpression expr = xpath.compile("/people/person[healthprofile/weight"+wt+"]");
        doc = builder.parse("xpathDB.xml");
	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	        System.out.println(nodes.item(i).getTextContent());
	    }
	    return null;
    }
   

    public static void main(String[] args) throws ParserConfigurationException, SAXException,
            IOException, XPathExpressionException {

        XPathPrinter xprint = new XPathPrinter();
        xprint.loadXML();

        try{
	//printing all people in the list with detail
	    System.out.println("***** People in our Database *********\n");
	    xprint.printPeople();
	    System.out.println("***** End of People in our Database *********\n\n");
	     
	    System.out.println("***** HealthProfile of person id 5 *********\n");
	    xprint.printHealthProfile(5);
	    System.out.println("***** End of HealthProfile of person id 5 *********\n\n");
	       
	       
	    System.out.println("***** People with weight > 90 *********\n");
		xprint.calcWeight(">90");
		 System.out.println("***** End of People with weight > 90 *********");
        
	}catch(Exception e){
		System.out.println(e);
		}

    }
}
