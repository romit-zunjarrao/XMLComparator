import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseUnknownXMLStructure
{
	
  
   public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
   {
	   
	   Vector repeatedUser = new Vector() ;
      //Get Docuemnt Builder
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      
      //Build Document
      Document document = builder.parse(new File("people.xml"));
      
      //Normalize the XML Structure; It's just too important !!
      document.getDocumentElement().normalize();
      
      //Here comes the root node
      Element root = document.getDocumentElement();
      System.out.println(root.getNodeName());

      
      //Get all employees
      NodeList nList = document.getElementsByTagName("person");
      System.out.println("============================");
      
      HashMap hashMap = new HashMap();
      
      /* The first and second loop are identical 
       only difference is that first loop will get a node element and check if id is already present 
       in hashMap or not.
       if present then it might be duplicate data and  will not add
       else will add
       key would be the id and value will be the string content of hashMap
       
       
       in second if the id is present in the hashmap for 2nd xml then it will get the record from hashmap
       and compare its string with the textContent of 2nd xml.
       if same then print the value
       if not then no value will be printed
       
       if the id is not present then the program does nothing
       */
      
      for (int temp = 0; temp < nList.getLength(); temp++)
      {
         Node node = nList.item(temp);
         if (node.getNodeType() == Node.ELEMENT_NODE)
         {
            System.out.println("Node Name = " + node.getNodeName() + "; Value = " + node.getTextContent());
            //Check all attributes
            if(hashMap.containsKey(((Element)node).getAttribute("id")))
            {
            	System.out.print("Element already present");
            }
            else
            {
    		Element eElement = (Element) node;
            hashMap.put(eElement.getAttribute("id"), node.getTextContent());
           }
         }
      }
      
      
      
      //logic for 2nd file
      factory = DocumentBuilderFactory.newInstance();
      builder = factory.newDocumentBuilder();
      
      //Build Document
      document = builder.parse(new File("People1.xml"));
      
      //Normalize the XML Structure; It's just too important !!
      document.getDocumentElement().normalize();
      
      //Here comes the root node
      root = document.getDocumentElement();
      System.out.println(root.getNodeName());

      
      //Get all employees
      nList = document.getElementsByTagName("person");
      System.out.println("============================");
      
 
      
      for (int temp = 0; temp < nList.getLength(); temp++)
      {
         Node node = nList.item(temp);
         if (node.getNodeType() == Node.ELEMENT_NODE)
         {
            System.out.println("Node Name = " + node.getNodeName() + "; Value = " + node.getTextContent());
            //Check all attributes
            if(hashMap.containsKey(((Element)node).getAttribute("id")))
            {
            	String user = (String) hashMap.get(((Element)node).getAttribute("id"));
            	if(user.equalsIgnoreCase(node.getTextContent()))
            			{
            				repeatedUser.add(user);
            			}
            }
         }
      }
      
      Iterator it = repeatedUser.iterator();
      
      System.out.println("Following are the repeated content in XML file 2 compared to XML File 1");
      while(it.hasNext())
      {
    	  System.out.println(it.next());
      }
   }
   

}

   