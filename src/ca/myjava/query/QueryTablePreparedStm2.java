package ca.myjava.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import ca.myjava.sqlInfo.SqlInfo;
//This class is created to fetch records within a certain range of life expectency at birth using PREPARED STATEMENT
public class QueryTablePreparedStm2 {
	//Declaring connection and prepared statement
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	//Created a parameterized constructor
	public QueryTablePreparedStm2(Connection connection, PreparedStatement preparedStatement) {
		super();
		this.connection = connection;
		this.preparedStatement = preparedStatement;
	}
	
	//Creating a default constructor to create a connection object using properties
	public QueryTablePreparedStm2() {
		super();
		try {
			//Load the driver class
			Class.forName(SqlInfo.DRIVER_CLASS_MYSQL);
			//fetch the connection object using driver manager
			connection = DriverManager.getConnection(SqlInfo.URL,SqlInfo.userName,SqlInfo.pwd);
			System.out.println("Connection Created");
		} 
		//Handle all exceptions
		catch (ClassNotFoundException classNotFoundException) {
			// TODO: handle exception
			classNotFoundException.printStackTrace();
		} catch (SQLException sqlException) {
			// TODO: handle exception
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	//getters and setters
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}
	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}
	
	
	//function to fetch records
	public void getCountriesHavingALifeExpectencyRange(int lowerLimt, int upperLimit) {
		try {
			
			//fetch the starting time
			long startTime = System.nanoTime();
			//create the prepared statement
			preparedStatement = connection.prepareStatement("select * from countries where life_expect_at_birth between ? and ?");
			preparedStatement.setFloat(1, lowerLimt);
			preparedStatement.setFloat(2, upperLimit);
			//execute the prepared statement and fetch data to result set
			ResultSet resultSet = preparedStatement.executeQuery();
			//Fetch the end time
			long endTime = System.nanoTime();
			//Calculate how much time it took for the execution
			long elapsedTime = endTime - startTime;
			System.out.println("Execution time in milliseconds when using a prepared statement: " + elapsedTime / 1000000);
			
			//Display result set
			while (resultSet.next()) {
				int int1 = resultSet.getInt(1);
				int int2 = resultSet.getInt(2);
				String string1 = resultSet.getString(3);
				String string2 = resultSet.getString(4);
				String string3 = resultSet.getString(5);
				String string4 = resultSet.getString(6);
				//System.out.println(int1+"\t\t"+int2+"\t\t"+string1+"\t\t"+string2+"\t\t"+string3+"\t\t"+string4);
				System.out.println(String.format("%20s %20s %30s %20s %20s %20s \r\n", int1,int2,string1,string2,string3,string4));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		//create object with default constructor which will create the connection
		QueryTablePreparedStm2 queryTablePreparedStm2 = new QueryTablePreparedStm2();
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter lower limit for life expectancy at birth range");
		int lowerLimt = sc.nextInt();
		System.out.println("Enter upper limit for life expectancy at birth range");
		int upperLimit = sc.nextInt();
		//Calling the desired method
		queryTablePreparedStm2.getCountriesHavingALifeExpectencyRange(lowerLimt,upperLimit);
		
	}
	
}
