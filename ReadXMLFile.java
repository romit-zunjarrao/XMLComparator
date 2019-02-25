import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.HashMap;

public class ReadXMLFile {

  public static void main(String argv[]) {

	HashMap hashMap = new HashMap(); // to store 1st file element
	  
	try {

	//below 4 lines just parses the file
	File fXmlFile = new File("E:/Udemy/Java Programs/rutuja/people.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	// Gives the root element i.e person from Poeple file in our case
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
	NodeList nList = doc.getElementsByTagName("person");
		
	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
			
			//below comment lines are just to check whether we are able to access the value or not
			/*
			System.out.println("Staff id : " + eElement.getAttribute("id"));
			System.out.println(eElement.getElementsByTagName("firstname").item(0).getTextContent());
			System.out.println(eElement.getElementsByTagName("lastname").item(0).getTextContent());
			*/
			
			if(hashMap.containsKey(eElement.getAttribute("id")))
			{ 
				//this if statement does nothing just gives a message
				System.out.println("Already present");
			}
			else
			{
				//actual logic for hashMap entry. if it a new element from our people file then add it to hashmap
				//first you need to create a people Object
				//people class is written because hashMap store keyvalue
				//in our case i pust id as key and poeple object as value
				//People object has 1st name and last name
				People person = new People(eElement.getElementsByTagName("firstname").item(0).getTextContent(),eElement.getElementsByTagName("lastname").item(0).getTextContent());
				hashMap.put(eElement.getAttribute("id"), person);
			}
		}
	}
	
	
	//this is where we start comparing the 2nd xml file by getting file by repeating the previous steps
		    fXmlFile = new File("E:/Udemy/Java Programs/rutuja/People1.xml");
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
			 nList = doc.getElementsByTagName("person");
			
			 
			 for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);
							
					System.out.println("\nCurrent Element :" + nNode.getNodeName());
							
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						/*System.out.println("Staff id : " + eElement.getAttribute("id"));
						System.out.println(eElement.getElementsByTagName("firstname").item(0).getTextContent());
						System.out.println(eElement.getElementsByTagName("lastname").item(0).getTextContent());
						*/
						
						if(hashMap.containsKey(eElement.getAttribute("id")))
						{
							//if we already have the element we just print it and if we dont we carry on with loop execution
							People people = (People)hashMap.get(eElement.getAttribute("id"));
							System.out.print(people.firstname + " reapted"+ people.lastname);
						}
						
					}
				}
			
			
    } catch (Exception e) {
	e.printStackTrace();
    }
	//this print is to check hashMap contents
	System.out.println(hashMap);
	  
	  
	  
	  
  }

}

class People
{
	@Override
	public String toString() {
		return "People [firstname=" + firstname + ", lastname=" + lastname + "]";
	}

	String firstname,lastname;
	
	People(String firstname,String lastname)
	{
		this.firstname = firstname;
		this.lastname = lastname;
	}
}