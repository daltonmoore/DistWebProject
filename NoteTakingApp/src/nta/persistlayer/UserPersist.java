package nta.persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nta.objectlayer.User;
import nta.persistlayer.DatabaseAccess;

public class UserPersist {

	//Adds a new user to the database
		public static void createNewUser(User user) {
			String query = "insert into users values(" + 0 + ",'"+ user.getUsername() + "','" + user.getPassword() + "','" 
					+ user.getEmail() + "','" + user.getFirstname() + "','" + user.getLastname() + "')";
			
			DatabaseAccess.create(query);
			
		}
	
	
		//used to validate a given username has the appropriate password
		public static boolean authenticate(String username,String password) {
			boolean valid = false;
			String query =  "SELECT username, password FROM users WHERE username='"+username+"';";
			
			ResultSet rs = DatabaseAccess.retrieve(query);
				
			String rsUsername = "";
			String rsPass="";
			try {
				while (rs.next()) {
					rsUsername = rs.getString("username");
					rsPass = rs.getString("password");
					System.out.println(username + " " + password+ " " + rsUsername + " " + rsPass);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(rsPass.equals(password) && rsUsername.equals(username)) {
				valid = true;
			}else { 
				valid=false;
			}
			
			return valid;
		}
}
