package nta.persistlayer;

import java.sql.ResultSet;

public class CategoryPersist {

	public static ResultSet getCategoriesForAccount(int userid) {
		String query = "Select * from category where accountid="+userid;
		
		ResultSet results = DatabaseAccess.retrieve(query);
		
		
		return results;
	}

}
