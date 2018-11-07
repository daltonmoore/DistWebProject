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
			//Insert user into db
			String query = "insert into users(accountid,username,password,email,firstname,lastname) values("+0+",'"+ user.getUsername() + "','" + user.getPassword() + "','" 
					+ user.getEmail() + "','" + user.getFirstname() + "','" + user.getLastname() + "');";
			
			DatabaseAccess.create(query);
			
			//Insert Category entry into db
			String query2 = "Insert into category(accountid) select accountid from users where username='"+user.getUsername()+"';";
			
			DatabaseAccess.create(query2);
			
		}
	
	
		//used to validate a given username has the appropriate password
		public static ResultSet authenticate(String username,String password) {
			String query =  "SELECT username, password FROM users WHERE username='"+username+"';";
			
			ResultSet rs = DatabaseAccess.retrieve(query);
				
			return rs;
		}


		public static ResultSet getUserIdByUsername(String username) {
			String query = "SELECT accountid FROM users WHERE username='"+username+"';";
			
			ResultSet rs = DatabaseAccess.retrieve(query);
			
			return rs;
		}
}
