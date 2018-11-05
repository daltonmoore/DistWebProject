package nta.persistlayer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//use this class to run queries and such as well as get a SINGLE connection instead of connecting to the database continuously
public class DatabaseAccess {
	DatabaseUtils dbutil = new DatabaseUtils();
	
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DatabaseConfiguration.mysqlURL, DatabaseConfiguration.username, DatabaseConfiguration.password);
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return con;
	}

//used to validate a given username has the appropriate password
	public boolean authenticate(String username,String password) {
		boolean valid = false;
		ResultSet rs = null;
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			String sql =  "SELECT username, password FROM users WHERE username='"+username+"';";
			rs = st.executeQuery(sql);
			
			String rsUsername = "";
			String rsPass="";
			while (rs.next()) {
				rsUsername = rs.getString("username");
				rsPass = rs.getString("password");
				System.out.println(username + " " + password+ " " + rsUsername + " " + rsPass);
			}
			if(rsPass.equals(password) && rsUsername.equals(username)) {
				valid = true;
			}else { 
				valid=false;
			}
		
			//close statement and result set	
			dbutil.close(st, rs);
		
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
		
		return valid;
		
	}

//Adds a new user to the database
	public void createUser(String newUsername, String newPassword, String email, String firstName, String lastName) {
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			String query = "insert into users values(" + 0 + ",'"+ newUsername + "','" + newPassword + "','" 
					+ email + "','" + firstName + "','" + lastName + "')";
			st.executeUpdate(query);
		}catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}
	
}
