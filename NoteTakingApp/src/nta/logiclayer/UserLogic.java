package nta.logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import nta.objectlayer.User;
import nta.persistlayer.DatabaseAccess;
import nta.persistlayer.UserPersist;

public class UserLogic {
	
	public static void createUser(User u) {
		UserPersist.createNewUser(u);
		DatabaseAccess.disconnect();
	}
	
	public static boolean authenticateUser(String username, String password) {
		boolean valid = false;
		ResultSet auth = UserPersist.authenticate(username, password);
		
		String rsUsername = "";
		String rsPass="";
		try {
			while (auth.next()) {
				rsUsername = auth.getString("username");
				rsPass = auth.getString("password");
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
		
		DatabaseAccess.disconnect();
		return valid;
	}

	public static int getUserIdByUsername(String username) {
		ResultSet result = UserPersist.getUserIdByUsername(username);
		
		int userid = 0;
		
		try {
			while(result.next()) {
				userid = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userid;
	}
	
}
