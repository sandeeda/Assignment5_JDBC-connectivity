package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import ca.myjava.sqlInfo.SqlInfo;
//Update using prepared st.
public class UpdateTablePreparedStm {
	
	//Declare the connection and prepared statement
	private Connection connection;
	private PreparedStatement preparedStatement;
	//Parameterized constructor
	public UpdateTablePreparedStm(Connection connection, PreparedStatement preparedStatement) {
		super();
		this.connection = connection;
		this.preparedStatement = preparedStatement;
	}
	
	//Default constructor
	public UpdateTablePreparedStm() {
		super();
		//get the connection
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
	
	//getter and setter
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
	
	//function to update
	public void updateCountryTranslatedName(int countryId, String updatedCountryTranslatedName) {
		try {
			long startTime = System.nanoTime();
			//prepared statement with a new country translated name and country id where the update has to be done
			preparedStatement = connection.prepareStatement("update countries set COUNTRY_TRANSLATED_NAME = ? where COUNTRY_ID = ?");
			//Parameterize the prepared statement
			preparedStatement.setInt(2,countryId);
			preparedStatement.setString(1, updatedCountryTranslatedName);
			//get the number of rows updated
			int numberOfRowsUpdated = preparedStatement.executeUpdate();
			//get the end time
			long endTime = System.nanoTime();
			//calculate time elapsed to finish execution
			long elapsedTime = endTime - startTime;
			System.out.println("Execution time in milliseconds when using a prepared statement: " + elapsedTime / 1000000);
			System.out.println(numberOfRowsUpdated+" rows updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//create object and call the update function
		UpdateTablePreparedStm updateTablePreparedStm = new UpdateTablePreparedStm();
		//display the update query to user
		System.out.println("SQL statement : update countries set COUNTRY_TRANSLATED_NAME = ? where COUNTRY_ID = ?");
		//Read data
		System.out.println("Please enter the parameters for Country translated name and the country ID");
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\r?\n");

		System.out.println("Country translate name: ");
		String countryTranslatedName = scanner.next();
		System.out.println("Country ID: ");
		int countryId = scanner.nextInt();
		
		//call the function to execute update
		updateTablePreparedStm.updateCountryTranslatedName(countryId,countryTranslatedName);
		
	}
}
