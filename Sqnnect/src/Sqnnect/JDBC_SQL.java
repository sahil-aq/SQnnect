package Sqnnect;
import java.sql.*;
import java.util.*;
public class JDBC_SQL 
{

	public static void main(String[] args) 
	{
		int option=0;
		Scanner scanner = new Scanner(System.in);
		while(true) 
		{
			System.out.println("\n****************************************************");
			System.out.println("Select Queries for which you want to see data table;");
			System.out.println("****************************************************\n");
	        
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
	        if(option == 0)
	        {
	        	System.out.print("Exiting.........");
	        	System.exit(0);
	        }else if(option < 0)
	        {
	        	System.out.println("Wrong input positive integer only :(");
	            continue;
	        }
	        try 
			{
	
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");
				Statement statement = connection.createStatement();	
				String localQuery="Select Queries from sqnnect_table where id = " + option + ";";
	            ResultSet resultSet = statement.executeQuery(localQuery);
	            while (resultSet.next()) {
	            	String midSet = resultSet.getString(1);
	            	ResultSet finalResult = statement.executeQuery(midSet);
		            DBTablePrinter.printResultSet(finalResult);
		            break;
	            }
	            
	      
	        }
	         catch (Exception e) 
			{
	            e.printStackTrace();
	        }
			
		}
	}
}







