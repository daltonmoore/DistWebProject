package nta.logiclayer;

import nta.objectlayer.User;
import nta.persistlayer.DatabaseAccess;
import nta.persistlayer.UserPersist;

public class UserLogic {
	
	public static void createUser(User u) {
		UserPersist.createNewUser(u);
		DatabaseAccess.disconnect();
	}
	
	public static boolean authenticateUser(String username, String password) {
		boolean valid = UserPersist.authenticate(username, password);
		DatabaseAccess.disconnect();
		return valid;
	}
	
}
