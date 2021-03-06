
package nta.persistlayer;

import java.sql.ResultSet;

import nta.objectlayer.Notes;

public class NotesPersist {

	public static ResultSet getArchivedNotesForAccountId(int userid) {
		String query = "SELECT * from notes where accountid="+userid+" and statusid="+2;
		
		ResultSet results = DatabaseAccess.retrieve(query);
		
		return results;
	}
	
	public static ResultSet getNotesForAccountId(int userid) {
		String query = "SELECT * from notes where accountid="+userid+" and statusid="+1;
		
		ResultSet results = DatabaseAccess.retrieve(query);
		
		return results;
		
	}

	public static ResultSet createNewNote(Notes note) {
		String query = "Insert into notes(NoteTitle,NoteContent,Color,AccountID,CategoryID,StatusID) "
				+ "VALUES('"+note.getNoteTitle()+"','"+note.getNoteContent()+"','"+note.getColor()+"',"+note.getAccountID()+","+
				note.getCategoryID()+","+note.getStatusID()+");";
		String query2 = "Select last_insert_id();";
		
		ResultSet results = DatabaseAccess.createAndReturnKey(query,query2);
		
		return results;
	}

	public static int updateNote(Notes note) {
		int numRowsAffected = 0;
		String query = "Update notes set NoteTitle='"+note.getNoteTitle()+"', NoteContent='"+note.getNoteContent()+"', Color='"
				+note.getColor()+"', CategoryID="+note.getCategoryID()+", StatusID="+note.getStatusID()+" WHERE NoteID="+note.getNoteID();
		
		numRowsAffected = DatabaseAccess.update(query);
		System.out.println(numRowsAffected);
		return numRowsAffected;
	}

	public static int deleteNote(String deleteid) {
		int numRowsAffected = 0;
		String query = "Delete from notes where NoteID="+deleteid;
		
		numRowsAffected = DatabaseAccess.delete(query);
		
		return numRowsAffected;
	}

}
