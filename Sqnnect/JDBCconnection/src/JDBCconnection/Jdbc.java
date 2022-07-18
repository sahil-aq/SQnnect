package JDBCconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Jdbc {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("****************************************************");
		System.out.println("Select Queries for which you want to see data table;");
		System.out.println("****************************************************\n");
		System.out.println("1. Get all the products of a Supplier sorted in order of their price.\r\n"
				+ "2. Add price of all the products for all Categories and get the result in sorted order of their price.\r\n"
				+ "3. Get order details for all customers.\r\n"
				+ "4. Get order details and products associated with a particular order for a particular customer.\r\n"
				+ "5. Get all the products for a Supplier in a row. [Products should be separated by comma].\r\n"
				+ "6. Write a function to calculate the total price of a Order [Parameter to pass Order Id] \r\n"
				+ "7. Create a view of customers, order details and orders. Name the view as ‘vw_customers_order’.\r\n"
				+ "8.Create a new table consisting of “customer id”,”customer_name”, “all_orders”. Apply  (while / for)"
				+ " \nloop in Stored procedure to get list of customers and then for every customer fetch all his "
				+ "\norders separated by “,”. Insert the same into the newly created table\r\n"
				+ "9.Create a table consisting of all the columns present in ‘vw_customers_order’. "
				+ "\nWrite a Stored procedure to read from this view and insert all the entries in "
				+ "\nthe newly created table. Name Stored Procedure as ‘sp_insert_customers_order’\r\n"
				+ "10.Create a table consisting of all the columns present in ‘vw_customers_order’. "
				+ "\nWrite a Stored procedure to read from this view and insert all the entries in the"
				+ "\nnewly created table for a given customer. Name Stored Procedure as ‘sp_insert_customers_order(customer_id int)’\r\n"
				+ "");
		System.out.print("Enter the choice :");
        int ch = scanner.nextInt();
        switch(ch) {
        case 1:
		// TODO Auto-generated method stub
		try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");

            Statement statement = connection.createStatement();
            

                ResultSet resultSet = statement.executeQuery("select suppliers.SupplierID,ProductName,UnitPrice from products " +
                        "inner join suppliers on products.SupplierID=suppliers.SupplierID where suppliers.SupplierID='1' order by UnitPrice");
                System.out.println();

                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getDouble(3));
                }
      
            }
         catch (Exception e) {
            e.printStackTrace();
        }
		break;
        case 2:
        	try {

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");

                Statement statement = connection.createStatement();
                

                    ResultSet resultSet = statement.executeQuery("SELECT categories.categoryname, Sum(products.unitprice) as TotalPrice from categories inner join\r\n"
                    		+ "products on products.CategoryID=categories.CategoryID GROUP BY categoryname ORDER BY TotalPrice;\r\n"
                    		+ "\r\n"
                    		+ "");

                    while (resultSet.next()) {
                        System.out.println(resultSet.getString(1) + " " + resultSet.getDouble(2));
                    }
          
                }
             catch (Exception e) {
                e.printStackTrace();
            }
        	break;
        case 3:
    		// TODO Auto-generated method stub
    		try {

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");

                Statement statement = connection.createStatement();
                

                    ResultSet resultSet = statement.executeQuery("SELECT customers.contactName, `order details`.* from ((`order details` INNER JOIN orders ON orders.OrderID=`order details`.OrderID)\r\n"
                    		+ "inner join customers on orders.CustomerID=customers.CustomerID);\r\n"
                    		+ "");

                    while (resultSet.next()) {
                        System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2) + " " + resultSet.getInt(3)+" "+resultSet.getDouble(4)+" "+resultSet.getInt(5)+" "+resultSet.getInt(6));
                    }
          
                }
             catch (Exception e) {
                e.printStackTrace();
            }
    		break;
        case 4:
    		// TODO Auto-generated method stub
    		try {

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");

                Statement statement = connection.createStatement();
                

                    ResultSet resultSet = statement.executeQuery("SELECT customers.contactName, products.productName,`order details`.* from ((\r\n"
                    		+ "(`order details` INNER JOIN orders ON orders.OrderID=`order details`.OrderID)\r\n"
                    		+ "inner join products on products.productId=`order details`.productID)\r\n"
                    		+ "inner join customers on orders.CustomerID=customers.CustomerID) WHERE ContactName='Maria Anders';\r\n"
                    		+ "");

                    while (resultSet.next()) {
                        System.out.println(resultSet.getString(1)+ " "+resultSet.getString(2) + " " + resultSet.getInt(3) + " " + resultSet.getInt(4)+" "+resultSet.getDouble(5)+" "+resultSet.getInt(6)+" "+resultSet.getInt(7));
                    }
          
                }
             catch (Exception e) {
                e.printStackTrace();
            }
    		break;
        case 5:
    		// TODO Auto-generated method stub
    		try {

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");

                Statement statement = connection.createStatement();
                

                    ResultSet resultSet = statement.executeQuery("SELECT group_concat(productName) AS Product from products WHERE SupplierID = '6';\r\n"
                    		+ "");

                    while (resultSet.next()) {
                        System.out.println(resultSet.getString(1));
                    }
          
                }
             catch (Exception e) {
                e.printStackTrace();
            }
    		break;
        case 6:
    		// TODO Auto-generated method stub
    		try {

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");

                Statement statement = connection.createStatement();
                

                    ResultSet resultSet = statement.executeQuery("delimiter //\r\n"
                    		+ "CREATE FUNCTION Totalpriceorder(od1 int) \r\n"
                    		+ "Returns float\r\n"
                    		+ "deterministic\r\n"
                    		+ "BEGIN\r\n"
                    		+ "declare sumoforder float;\r\n"
                    		+ "SET @sumOfOrder := (SELECT SUM(unitprice*quantity) FROM `order details` WHERE `order details`.orderid = od1);\r\n"
                    		+ "return @sumOfOrder;\r\n"
                    		+ "end //\r\n"
                    		+ "delimiter ;\r\n"
                    		+ "");

                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt(1));
                    }
          
                }
             catch (Exception e) {
                e.printStackTrace();
            }
    		break;
        case 7:
    		// TODO Auto-generated method stub
    		try {

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");

                Statement statement = connection.createStatement();
                

                    ResultSet resultSet = statement.executeQuery("SELECT * from vw_customers_order;\r\n"
                    		+ "");

                    while (resultSet.next()) {
                    	System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3)+" "+resultSet.getDouble(4)+" "+resultSet.getInt(5)+" "+resultSet.getInt(6));
                    }
          
                }
             catch (Exception e) {
                e.printStackTrace();
            }
    		break;
        case 8:
    		// TODO Auto-generated method stub
    		try {

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");

                Statement statement = connection.createStatement();
                

                    ResultSet resultSet = statement.executeQuery("SELECT Customers.customerID, Customers.ContactName, group_concat(Orders.orderID) from Customers inner join orders on customers.customerID = orders.customerID;\r\n"
                    		+ "");

                    while (resultSet.next()) {
                    	System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                    }
          
                }
             catch (Exception e) {
                e.printStackTrace();
            }
    		break;
        case 9:
    		// TODO Auto-generated method stub
    		try {

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");

                Statement statement = connection.createStatement();
                

                    ResultSet resultSet = statement.executeQuery("SELECT * from sp_insert_customer_order;\r\n"
                    		+ "");

                    while (resultSet.next()) {
                    	System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3)+ " " + resultSet.getInt(4)+ " " + resultSet.getDouble(5)+ " " + resultSet.getInt(6)+ " " + resultSet.getInt(7));
                    }
          
                }
             catch (Exception e) {
                e.printStackTrace();
            }
    		break;
        case 10:
    		// TODO Auto-generated method stub
    		try {

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "root");

                Statement statement = connection.createStatement();
                

                    ResultSet resultSet = statement.executeQuery("SELECT * from sp_insert_customer_order1 ;\r\n"
                    		+ "");

                    while (resultSet.next()) {
                    	System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3)+ " " + resultSet.getInt(4)+ " " + resultSet.getDouble(5)+ " " + resultSet.getInt(6)+ " " + resultSet.getInt(7));
                    }
          
                }
             catch (Exception e) {
                e.printStackTrace();
            }
    		break;
    		default:
    			System.out.print("Invalid");
		
    }
	}
	}


