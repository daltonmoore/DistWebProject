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
			Connection con = DatabaseAccess.getConnection();
			try {
				Statement st = con.createStatement();
				String query = "insert into users values(" + 0 + ",'"+ user.getUsername() + "','" + user.getPassword() + "','" 
						+ user.getEmail() + "','" + user.getFirstname() + "','" + user.getLastname() + "')";
				st.executeUpdate(query);
			}catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
	
	
		//used to validate a given username has the appropriate password
		public static boolean authenticate(String username,String password) {
			boolean valid = false;
			ResultSet rs = null;
			Connection con = DatabaseAccess.getConnection();
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
				DatabaseAccess.disconnect();
			
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
			
			return valid;
		}
}
