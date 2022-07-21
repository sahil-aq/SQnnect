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
      System.out.println("Student id: "+ eElement1.getElementsByTagName("id").item(0).getTextContent()); 
      System.out.println();
      }
      }
      System.out.println();
      
      
 
//         System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); 
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter the choice from 0-9");
         int ch = sc.nextInt();
         NodeList nodeList = doc.getElementsByTagName("value");
 
       
         Node node = nodeList.item(ch);   
         if (node.getNodeType() == Node.ELEMENT_NODE)   
         {  
         Element eElement = (Element) node;  
         String query = eElement.getElementsByTagName("id").item(0).getTextContent();
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
         
         }
        // }
         sc.close();
      }
      
         catch (Exception e)   
         {  
         e.printStackTrace(); 
   }
	 
   }}
   