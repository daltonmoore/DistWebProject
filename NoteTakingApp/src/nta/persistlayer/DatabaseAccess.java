
package nta.persistlayer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//use this class to run queries and such as well as get a SINGLE connection instead of connecting to the database continuously
public class DatabaseAccess {

	private static Connection con = null;
	
	public static Connection getConnection() {
		con = null;
		try {
			con = DriverManager.getConnection(DatabaseConfiguration.mysqlURL, DatabaseConfiguration.username, DatabaseConfiguration.password);
			System.out.println("Connection returned successfully.");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}

	public static ResultSet retrieve (String query) {
		//Get connection and initialize result set
		con = getConnection();
		ResultSet resultSet = null;
		
		try{   //create a statement and store the results in result set
			Statement statement = con.createStatement();
			resultSet = statement.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Result Set returned successfully.");
		
		return resultSet;
	}
	
	public static int create (String query) {
		con = getConnection();
		int numRowsAffected = 0;			//user to store number of affected rows
		try {
			Statement statement = con.createStatement();
			numRowsAffected = statement.executeUpdate(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(numRowsAffected + " were successfully created.");
		
		return numRowsAffected;
	}
	
	public static int update (String query) {
		con = getConnection();
		int numRowsAffected = 0;
		try {
			Statement statement = con.createStatement();
			numRowsAffected = statement.executeUpdate(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(numRowsAffected + " were successfully updated.");
		
		return numRowsAffected;
	}
	
	public static int delete (String query) {
		con = getConnection();
		int numRowsAffected = 0;
		try {
			Statement statement = con.createStatement();
			numRowsAffected = statement.executeUpdate(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(numRowsAffected + " were successfully deleted.");
		
		return numRowsAffected;
	}
	
	public static void disconnect() {
		if(con != null) {
			try {
				con.close();
				System.out.println("Closing db connection...");
			}catch (SQLException e) {
				System.out.println("DB connection failed to close...");
				e.printStackTrace();
			}
		}
	}
	
}
