package Sqnnect;

import java.sql.*;
import java.util.*;

/**
 * <strong> SQnnect_DB </strong><br>
 * A App for connecting JDBC Connection between MySQL and JAVA 
 * @author SahilSaiwal
 * @version 1.0
 * Date: 15/07/2022
 */

public class JDBC_SQL 
{
	/**
	 * This is a program for testing queries which are stored in 
	 * MySQL database in the form of table along with the unique id.
	 * @param args
	 */

	public static void main(String[] args) 
	{
		
		/**
		 * This is the main method which is very important for 
		 * execution for a java Program
		 */
		
		int option=0;
		
		/**
		 * @param option the options for switch cases
		 */
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) 
		{
			/**
			 * Loop for endless running untill user exits on itself selecting option from provided list
			 */
			
			
			System.out.println("\n****************************************************");
			System.out.println("Select Queries for which you want to see data table;");
			System.out.println("****************************************************");
	        
			try 
			{
	
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");
				Statement statement = connection.createStatement();	
	            ResultSet resultSet = statement.executeQuery("SELECT id, Problems from sqnnect_table;");
	            DBTablePrinter.printResultSet(resultSet);
	      
	        }
	         catch (Exception e) 
			{
	            e.printStackTrace();
	        }
			
			/**
			 * @throws CommunicationsException 
			 * 					thrown if connection between MySQL and java fails
			 * @param resultSet used for storing SQL table 
			 * 
			 * {@link Sqnnect.DBTablePrinter#printResultSet} used for printing tables stored in resultSet in table format 
			 */
			
			System.out.println("\n****************************************************");
			System.out.println("Enter \"0\" for exiting program");
			System.out.println("****************************************************\n");
			System.out.print("Enter the choice :");
	        String choice = scanner.next();
	        
	        try{
	            option = Integer.parseInt(choice);
	         } 
	         catch (NumberFormatException exception){
	            System.out.println("Wrong input positive integer only :(");
	            continue;
	         }
	        
	        /**
	         * @throws illegalArgumentException
	         * 				thrown if input is anything other than integer
	         */
	        
	        if(option == 0)
	        {
	        	System.out.print("Exiting.........");
	        	System.exit(0);
	        }else if(option < 0)
	        {
	        	System.out.println("Wrong input positive integer only :(");
	            continue;
	        }
	        
	        /**
	         * Condition for option == 0 it will show error in input and jumps back to where loop started
	         */
	        
	        try 
			{
	
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");
				Statement statement = connection.createStatement();	
				String localQuery="Select Queries from sqnnect_table where id = " + option + ";";
	            ResultSet resultSet = statement.executeQuery(localQuery);
	            while (resultSet.next()) {
	            	String midSet = resultSet.getString(1);
	            	System.out.print("Query:");
	            	System.out.println(midSet);
	            	System.out.print("Enter value for <updatevalue>");
	            	if (midSet.contains("<updateValue>")) {
	            		
	            		System.out.print("Enter value :");
	            		scanner.nextLine();
	            		String userinput = scanner.nextLine();
	            		midSet = midSet.replace("<updateValue>",userinput);
	            	}
	            		
	            	
	            	ResultSet finalResult = statement.executeQuery(midSet);
		            DBTablePrinter.printResultSet(finalResult);
		            
		            break;
	            }
	            
	      
	        }
	         catch (Exception e) 
			{
	            e.printStackTrace();
	        }
	        /**
			 * @throws CommunicationsException 
			 * 					thrown if connection between MySQL and java fails
			 * @param resultSet used for storing SQL table 
			 * @param localQuery stored query
			 * @param midSet used to store query stored in SQL table in query column
			 * {@link Sqnnect.DBTablePrinter#printResultSet} used for printing tables stored in resultSet in table format 
			 */
			
		}
	}
}







