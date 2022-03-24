package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ca.myjava.sqlInfo.SqlInfo;

//Delete using result set
public class UpdateTableUpdatedResultSet {

	//declare the connection and prepared statement 
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	//Create the parameterized constructor
	public UpdateTableUpdatedResultSet(Connection connection, PreparedStatement preparedStatement) {
		super();
		this.connection = connection;
		this.preparedStatement = preparedStatement;
	}
	
	//Default constructor that establishes the connection
	public UpdateTableUpdatedResultSet() {
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
	
	//delete the record using the deleteRow method
	public void updateCountryTranslatedName(int countryId) {
		try {
			//create the statement which is updatable and scroll insensitive
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			         ResultSet.CONCUR_UPDATABLE);
			//create the result set using the statement
			ResultSet resultSetBeforeUpdate = statement.executeQuery("select COUNTRY_ID,\r\n"
					+ "REGION_ID,\r\n"
					+ "COUNTRY_NAME,\r\n"
					+ "COUNTRY_TRANSLATED_NAME,\r\n"
					+ "LOCATION,\r\n"
					+ "CAPITOL,\r\n"
					+ "AREA,\r\n"
					+ "COASTLINE,\r\n"
					+ "LOWEST_ELEVATION,\r\n"
					+ "LOWEST_ELEV_NAME,\r\n"
					+ "HIGHEST_ELEVATION,\r\n"
					+ "HIGHEST_ELEV_NAME,\r\n"
					+ "DATE_OF_INDEPENDENCE,\r\n"
					+ "NATIONAL_HOLIDAY_NAME,\r\n"
					+ "NATIONAL_HOLIDAY_DATE,\r\n"
					+ "POPULATION,\r\n"
					+ "POPULATION_GROWTH_RATE,\r\n"
					+ "LIFE_EXPECT_AT_BIRTH,\r\n"
					+ "MEDIAN_AGE,\r\n"
					+ "AIRPORTS,\r\n"
					+ "CLIMATE,\r\n"
					+ "FIPS_ID,\r\n"
					+ "INTERNET_EXTENSION,\r\n"
					+ "FLAG,\r\n"
					+ "CURRENCY_CODE from countries where country_id="+countryId);
			
			
			
			//go to the first record
			resultSetBeforeUpdate.next();
			
			//Delete the row
			try {
				resultSetBeforeUpdate.deleteRow();
				//Show successful delete message
				System.out.println("1 row deleted");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Row unavailable for delete");
				e.printStackTrace();
			}
		
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//Create an object of the class and pass the parameters to the function to reflect the updates
		UpdateTableUpdatedResultSet updateTableUpdatedResultSet = new UpdateTableUpdatedResultSet();
		int countryId;
		Scanner sc = new Scanner(System.in);
		
		
		countryId = sc.nextInt();
		updateTableUpdatedResultSet.updateCountryTranslatedName(countryId);
		
	}
}
