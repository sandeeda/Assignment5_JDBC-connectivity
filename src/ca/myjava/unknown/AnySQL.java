package ca.myjava.unknown;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ca.myjava.sqlInfo.SqlInfo;

public class AnySQL {
	//declaring statics to change console output colour
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String RESET = "\033[0m";

    //Creating connections and statements
	private Connection connection;
	private Statement statement;
	
	//Parameterized constructor
	public AnySQL(Connection connection, Statement statement) {
		super();
		this.connection = connection;
		this.statement = statement;
	}
	
	//Default constructor which creates the connection
	public AnySQL() {
		super();
		try {
			Class.forName(SqlInfo.DRIVER_CLASS_MYSQL);
			connection = DriverManager.getConnection(SqlInfo.URL,SqlInfo.userName,SqlInfo.pwd);
			System.out.println("Connection Created");
		} catch (ClassNotFoundException classNotFoundException) {
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
	
	//Setters and getters
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	
	
	//function to fetch desired results. 
	public void runSQLCommand(String command) {
		try {
			System.out.println("SQL Loaded : "+ PURPLE_BOLD +
					command + RESET);
			
			//fetch start time
			long startTime = System.nanoTime();
			statement = connection.createStatement();
			System.out.println();
			//Check if the command consists of a select statement, if it consists of a select statement, then use executeQuery
			if(command.contains("select")) {
				ResultSet resultSet = statement.executeQuery(command);
				//fetch metadata to find number of columns fetched
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				//extract number of columns
				int numberOfColumns = resultSetMetaData.getColumnCount();
				//display data
				while (resultSet.next()) {
					for(int i = 1 ; i<=numberOfColumns ; i++) {
						//use metadata to get column name
						System.out.print(resultSetMetaData.getColumnName(i)+": "+resultSet.getObject(i)+" ");
					}
					System.out.println();
				}
			}
			//if the command does not have a select statement, use updateQuery
			else {
				int numOfRows = statement.executeUpdate(command);
				System.out.println(numOfRows+" updated");
			}
			
			
			//fetch end time
			long endTime = System.nanoTime();
			//calculate the duration of the execution
			long elapsedTime = endTime - startTime;
			System.out.println("Execution time in milliseconds : " + elapsedTime / 1000000);
			System.out.println();
			
			
			//handle exception
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//create an object
		AnySQL anySQL = new AnySQL();
		//scanner to read details
		Scanner scanner = new Scanner(System.in);
		
		//read the statement
		System.out.println("Enter the SQL command");
		String command = scanner.nextLine();
		
		//pass the inputs and fetch the data
		anySQL.runSQLCommand(command);
		
	}

	
}
