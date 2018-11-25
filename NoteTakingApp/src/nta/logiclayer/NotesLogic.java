
package nta.logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nta.objectlayer.Notes;
import nta.persistlayer.DatabaseAccess;
import nta.persistlayer.NotesPersist;
import nta.persistlayer.UserPersist;

public class NotesLogic {

	public static List<Notes> getArchivedNotesForAccountId(int userid){
		List<Notes> notes = new ArrayList<Notes>();
		ResultSet results = NotesPersist.getArchivedNotesForAccountId(userid);
		try {
			while(results.next()) {
				Notes note = new Notes();
				note.setNoteID(results.getInt(1));
				note.setNoteTitle(results.getString(2));
				note.setNoteContent(results.getString(3));
				note.setColor(results.getString(4));
				note.setAccountID(results.getInt(5));
				note.setCategoryID(results.getInt(6));
				note.setStatusID(results.getInt(7));
				notes.add(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Returning list of notes with size: " + notes.size());
		DatabaseAccess.disconnect();
		return notes;
	}
	
	public static List<Notes> getNotesForAccountId(int userid) {
		List<Notes> notes = new ArrayList<Notes>();
		
		ResultSet results = NotesPersist.getNotesForAccountId(userid);
		
		try {
			while(results.next()) {
				Notes note = new Notes();
				note.setNoteID(results.getInt(1));
				note.setNoteTitle(results.getString(2));
				note.setNoteContent(results.getString(3));
				note.setColor(results.getString(4));
				note.setAccountID(results.getInt(5));
				note.setCategoryID(results.getInt(6));
				note.setStatusID(results.getInt(7));
				notes.add(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Returning list of notes with size: " + notes.size());
		DatabaseAccess.disconnect();
		return notes;
	}
	
	public static int createNewNote(Notes note) {
		int NoteID = 0;
		ResultSet resultset = NotesPersist.createNewNote(note);
		
		try {
			while(resultset.next()) {
				NoteID = resultset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DatabaseAccess.disconnect();
		return NoteID;
	}

	public static int updateNote(Notes note) {
		int numRowsAffected=0;
		numRowsAffected = NotesPersist.updateNote(note);
		DatabaseAccess.disconnect();
		return numRowsAffected;
	}

	public static int deleteNote(String deleteid) {
		int numRowsAffected = NotesPersist.deleteNote(deleteid);
		return numRowsAffected;
	}

}

