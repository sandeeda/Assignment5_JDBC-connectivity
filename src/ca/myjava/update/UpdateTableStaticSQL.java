package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ca.myjava.sqlInfo.SqlInfo;
//insert using a static sql statement
public class UpdateTableStaticSQL {
	//create a connection and statement
	private Connection connection;
	private Statement statement;
	
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String RESET = "\033[0m";
	
	//Parameterized constructor
	public UpdateTableStaticSQL(Connection connection, Statement statement) {
		super();
		this.connection = connection;
		this.statement = statement;
	}
	
	//Defult constructor which creates the connection
	public UpdateTableStaticSQL() {
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
	
	
	//create a function to insert the details using static sql
	public void updateCountryTranslatedName(int countryId, int regionId, String countryName, String countryTranslatedName, String location, String capitol, String currencyCode) {
		try {
			long startTime = System.nanoTime();
			statement = connection.createStatement();
			//prepare the sql query including all the not null values and proper foreign key
			String sql = "insert into countries (country_id, region_id, country_name, country_translated_name,location,capitol,CURRENCY_CODE) values (" +countryId+","+regionId+",'"+countryName+"','"+countryTranslatedName+"','"+location+"','"+capitol+"','"+currencyCode+"')";
			
			System.out.println(PURPLE_BOLD+"SQL executing: "+sql+" "+RESET);
			int numberOfRowsUpdated = statement.executeUpdate(sql);
			long endTime = System.nanoTime();
			long elapsedTime = endTime - startTime;
			System.out.println("Execution time in milliseconds when using a prepared statement: " + elapsedTime / 1000000);
			System.out.println(numberOfRowsUpdated+" rows inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		//Create an object of the class and call the update method
		UpdateTableStaticSQL updateTableStaticSQL = new UpdateTableStaticSQL();
		//Variables to read data
		int countryId;
		int regionId;
		String countryName;
		String countryTranslatedName;
		String location;
		String capitol;
		String currencyCode;
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\r?\n");
		
		//Read all data from the user
		System.out.println("Enter countryID: ");
		countryId = sc.nextInt();
		System.out.println("Enter regionId: ");
		regionId = sc.nextInt();
		System.out.println("Enter country Name: ");
		countryName = sc.next();
		System.out.println("Enter country translated name: ");
		countryTranslatedName = sc.next();
		System.out.println("Enter Location: ");
		location = sc.next();
		System.out.println("Enter capitol: ");
		capitol = sc.next();
		System.out.println("Currency Code: ");
		currencyCode = sc.next();
		
		//send data to function to feed parameters to sql query
		updateTableStaticSQL.updateCountryTranslatedName(countryId,regionId,countryName,countryTranslatedName,location,capitol,currencyCode);
		
	}
}
