package nta.persistlayer;

import java.sql.ResultSet;

public class NotesPersist {

	public static ResultSet getNotesForAccountId(int userid) {
		String query = "SELECT * from notes where accountid="+userid+"";
		
		ResultSet results = DatabaseAccess.retrieve(query);
		
		return results;
		
	}

}
