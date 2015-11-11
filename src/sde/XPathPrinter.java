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
import org.w3c.dom.Element;
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

	XPathExpression expr = xpath.compile("/people/person[@id =" + id + "]/healthprofile/weight");
	Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
	return node;
    }

    // Get Height of person with that id
    public Node getHeight(long id) throws XPathExpressionException {

	XPathExpression expr = xpath.compile("/people/person[@id =" + id + "]/healthprofile/height");
	Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
	return node;
    }

// Print all the people in the database
    public void printPeople() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {

	String expresn = "/people";
	doc = builder.parse("xpathDB.xml");
//Get root node from our expression
	Node node = (Node) xpath.compile(expresn).evaluate(doc, XPathConstants.NODE);

//Child nodes under the root node as a list
	NodeList c = node.getChildNodes();

	printResults(c);
    }

    // Print health profile of person with given ID
    public void printHealthProfile(long id) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {

	XPathExpression expr = xpath.compile("/people/person[@id =" + id + "]");
	doc = builder.parse("xpathDB.xml");
	Object result = expr.evaluate(doc, XPathConstants.NODESET);
	printResults((NodeList) result);

    }

    public void calcWeight(String wt) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {

	XPathExpression expr = xpath.compile("/people/person[healthprofile/weight" + wt + "]");
	doc = builder.parse("xpathDB.xml");
	Object result = expr.evaluate(doc, XPathConstants.NODESET);
	printResults((NodeList) result);
    }

//This method prints results of an expression passed to it from the xml file 
    public void printResults(NodeList c) {
	for (int i = 0; i < c.getLength(); i++) {
	    if (c.item(i) instanceof Element) {
		NodeList children = c.item(i).getChildNodes();
		//Print the person's id
		System.out.println("No: " + c.item(i).getAttributes().getNamedItem("id").getTextContent());
		for (int j = 0; j < children.getLength(); j++) {
		    if (children.item(j) instanceof Element) {
			//Check if child node is not under healthprofile
			if (!((Element) children.item(j)).getTagName().contains("healthprofile")) {
			    System.out.println(((Element) children.item(j)).getTagName() + " : " + children.item(j).getTextContent());
			} //Check if child node is healthprofile node
			else {
			    System.out.println("Healthprofile:");
			    NodeList nl = ((Element) children.item(j)).getChildNodes();

			    for (int k = 0; k < nl.getLength(); k++) {
				if (!nl.item(k).getNodeName().contains("#")) {
				    System.out.println(nl.item(k).getNodeName() + " " + nl.item(k).getTextContent());
				}
			    }
			}
		    }
		}
	    }
	    System.out.println("\n");
	}
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException,
	    IOException, XPathExpressionException {

	XPathPrinter xprint = new XPathPrinter();
	xprint.loadXML();

	try {
//	printing all people in the list with detail
	    System.out.println("***** People in our Database *********\n");
	    xprint.printPeople();
	    System.out.println("***** End of People in our Database *********\n\n");
//	     
	    System.out.println("***** HealthProfile of person id 5 *********\n");
	    xprint.printHealthProfile(5);
	    System.out.println("***** End of HealthProfile of person id 5 *********\n\n");

	    System.out.println("***** People with weight > 90 *********\n");
	    xprint.calcWeight(">90");
	    System.out.println("***** End of People with weight > 90 *********");

	} catch (Exception e) {
	    System.out.println(e);
	}

    }
}
