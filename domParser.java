import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//import xmlTraversingRevision.xmlTraversal;

import java.util.*;
public class domParser {
    static HashMap<String,HashMap<String,String>> finalResult = new HashMap<String,HashMap<String,String>>();
    static HashMap<String,HashMap<String,String>> requiredResult = new HashMap<String,HashMap<String,String>>();
    static HashMap<String,String> map=new HashMap<>();
    static HashMap<String,String> map1=new HashMap<>();
    static ArrayList<String> list = new ArrayList<>();
    static ArrayList<String> list1=new ArrayList<>();   
    public void readXmlAttributes() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
  {
      //HashMap<String,HashMap<String,String>> requiredResult = new HashMap<String,HashMap<String,String>>();

        /**
         * DocumentBuilderFactory.newInstance() return instance of XMLDom parser
         * We can also specify the ClassName directly for XMLParser in newInstance method like
         * DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance
         * ("com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl",null)
        */

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("E:/Udemy/Java Programs/rutuja/people.xml"));
        doc.normalizeDocument();
        Element root=doc.getDocumentElement();
    //  System.out.println ("Root element of the doc is :" + doc.getDocumentElement().getNodeName());
         /**
         * Getting Particular node details
         */
            if (doc.hasChildNodes()) {

             /**
             * Getting all child node from XMLDocument using
             * doc.getDocumentElement().getChildNodes() Or doc.getChildNodes()
            */
            //getAllNodeNAttribute(doc.getDocumentElement().getChildNodes());
                //requiredResult=   
                        getAllNodeNAttribute(doc.getChildNodes(),doc);
                System.out.println("********:"+requiredResult);

        }
           // return requiredResult;
    }
    }
    public static void getAllNodeNAttribute(NodeList nodes,Document doc) throws XPathExpressionException {            
        // HashMap<String,HashMap<String,String>> finalResult = new HashMap<String,HashMap<String,String>>();

        String rootElement=doc.getDocumentElement().getTagName();
        String rootNextChild=doc.getDocumentElement().getChildNodes().item(1).getNodeName();
        Element c=doc.getDocumentElement();
         Node node = null;
         Object z;
            Object y;

        for (int i = 0; i < nodes.getLength(); i++) {
            node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("Node Start: " + node.getNodeName() + " [Start]");
                if (node.hasAttributes()) {
                    // get attributes names and values
                    NamedNodeMap nodeAttrbs = node.getAttributes();
                    for (int j = 0; j < nodeAttrbs.getLength(); j++) {
                        Node attrNode = nodeAttrbs.item(j);
                        System.out.println("attr name: " + attrNode.getNodeName());
                        System.out.println("attr value: " + attrNode.getNodeValue());
                        XPathFactory xPathfactory = XPathFactory.newInstance();
                        XPath xpath = xPathfactory.newXPath();
                    System.out.println("******Root element****** :"+rootElement);

                    System.out.println("*******Root Element Next Child*******:"+rootNextChild);
                        xmlTraversal traverse= new xmlTraversal();
                        try {
                        //  finalResult=        traverse.gettingAttributes(attrNode.getNodeName(),attrNode.getNodeValue() );
                        } catch (DOMException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                       String xpathExp = "/"+rootElement+"/"+rootNextChild+"[@"+attrNode.getNodeName()+"=\"+"+attrNode.getNodeValue()+"\"]";                         
                        Node node1 = (Node) xpath.evaluate(xpathExp, doc, XPathConstants.NODE);
              //        System.out.println("AttributesValue:"+node.getTextContent());
             //         System.out.println(node.getNodeValue());

//                      System.out.println("Atrributes Name:"+node.getNodeName()); 
               //       
                        map.put(attrNode.getNodeValue(),node.getTextContent());
                 //     System.out.println("Values in MAP:"+map);
                        if(node.getNodeType()==Node.ENTITY_NODE){
                        for(int r=0;r<node.getTextContent().length();r++){
                    //  System.out.println("AtrributesName:"+node.getChildNodes().item(r));
                        //list.add(node.getChildNodes().item(r));
                        }}
                    }
                }
                if (node.hasChildNodes()) {
                    getAllNodeNAttribute(node.getChildNodes(),doc);
                    System.out.println("Node Close: " + node.getNodeName() + " [CLOSE]");


                    if(node.getNodeName()!=c.getTagName() &&node.getNodeName()!=c.getChildNodes().item(1).getNodeName()){
                    list.add(node.getNodeName());
                }}

                if (!node.hasChildNodes()) {
                   System.out.println("Node Close: " + node.getNodeName() + " [CLOSE]");
                }
            } else if ((nodes.getLength() <= 1)) {
                System.out.println("Node Text: " + node.getNodeValue());  
            } 
        }
      }
      public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
          domParser dom= new domParser();
          dom.readXmlAttributes();
      }}