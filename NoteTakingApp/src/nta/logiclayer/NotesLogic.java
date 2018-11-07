<<<<<<< HEAD
package nta.logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nta.objectlayer.Notes;
import nta.persistlayer.NotesPersist;

public class NotesLogic {

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
		return notes;
	}

}
=======
package nta.logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nta.objectlayer.Notes;
import nta.persistlayer.NotesPersist;

public class NotesLogic {

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
		return notes;
	}

}
>>>>>>> origin/master
