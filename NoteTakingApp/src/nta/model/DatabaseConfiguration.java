package nta.model;


public class DatabaseConfiguration {

	public static String username = "root";
	
	public static String password = "given_password";
	
	public static String dbname = "notetakingapp";
	
	public static String mysqlURL = "jdbc:mysql://localhost:3306/"+dbname+"?serverTimezone=UTC";
}
