package JDBCconnection;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class XPathParserDemo {

   public static void main(String[] args) {
	   String query = null;
      
      try {
         File inputFile = new File("C:\\Users\\yash\\eclipse-workspace\\JDBCconnection\\src\\data.xml");
//         System.out.println("file name"+inputFile);
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;

         dBuilder = dbFactory.newDocumentBuilder();

         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         NodeList nodeList1 = doc.getElementsByTagName("questions");  
      // nodeList is not iterable, so we are using for loop  
      for (int itr = 0; itr < 1; itr++)   
      {  
      Node node1 = nodeList1.item(itr);  
  //    System.out.println("\nNode Name :" + node1.getNodeName());  
      if (node1.getNodeType() == Node.ELEMENT_NODE)   
      {  
      Element eElement1 = (Element) node1;  
   System.out.println( eElement1.getElementsByTagName("id").item(0).getTextContent()); 
      System.out.println();
      }
      }
      System.out.println();
      
      
 
//         System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); 
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter the choice");
         int ch = sc.nextInt();
         NodeList nodeList = doc.getElementsByTagName("value");
  //      System.out.println(nodeList.getLength());
//       System.out.println(nodeList.item(1));
         
         for(int i = 0;i<=nodeList.getLength();i++)
         {
        	 Node node = nodeList.item(i);
        	 
        	 if (node.getNodeType() == Node.ELEMENT_NODE)   
             {  
             Element eElement = (Element) node;  
             String q = eElement.getAttribute("id");
        //    	System.out.println(q);
                 int a = Integer.parseInt(q);
                 if (a == ch) {
                    query = eElement.getTextContent();
                    break;

                 }
     //        System.out.println(eElement.getAttribute("id")==ch.toString());
             }
         }
        
    //    Node node = nodeList.item(ch);
 //       System.out.print(node);
 //       System.out.println(node.getNodeType());
 //       System.out.println(Node.ELEMENT_NODE );
      //   if (node.getNodeType() == Node.ELEMENT_NODE)   
     //    {  
     //    Element eElement = (Element) node;  
         
   //     System.out.print(eElement.getAttribute("id"));
//        for (int itr = 0; itr <= 22; itr++)   
//        { 
////        	Node node = nodeList.item(ch);   
////            if (node.getNodeType() == Node.ELEMENT_NODE)   
////            {  
////            Element eElement = (Element) node;  
//        	String q = eElement.getAttribute("id");
//       	System.out.println(q);
//            int a = Integer.parseInt(q);
//        	if (ch == a) {
//        		query = eElement.getTextContent(); 
//        		System.out.println(query);
//        		break;
//      	}
//        }
//        }
         
         try {
        	 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");
             Statement statement = connection.createStatement();
             

                 ResultSet rs = statement.executeQuery(query);
                 ResultSetMetaData rsmd = rs.getMetaData();
                 int column_count = rsmd.getColumnCount();
                 for(int i = 1;i<=column_count;i++)
                 {
                	 System.out.print(rsmd.getColumnLabel(i)+"\t\t");
                 }
                 System.out.println();
                 while(rs.next()) {
                	 for(int i =1;i<=column_count;i++)
                	 {
                		 System.out.print(rs.getString(i)+"\t\t\t");
                	 }
                	 System.out.println();
                 }
       
             }
          catch (Exception e) {
             e.printStackTrace();
         }
         
         
        // }
         sc.close();
      }
      
         catch (Exception e)   
         {  
         e.printStackTrace(); 
   }
//	 
   }}
   