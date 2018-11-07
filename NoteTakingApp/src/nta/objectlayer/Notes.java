package nta.objectlayer;

public class Notes {
	private int NoteID;
	private String NoteTitle;
	private String NoteContent;
	private String Color;
	private int AccountID;
	private int CategoryID;
	private int StatusID;
	
	public Notes() { };
	
	public Notes(int noteID, String noteTitle, String noteContent, String color, int accountID, int categoryID, int statusID) {
		this.NoteID = noteID;
		this.NoteTitle = noteTitle;
		this.NoteContent = noteContent;
		this.Color = color;
		this.AccountID = accountID;
		this.CategoryID = categoryID;
		this.StatusID = statusID;
	}

	public String getNoteTitle() {
		return NoteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		NoteTitle = noteTitle;
	}

	public int getNoteID() {
		return NoteID;
	}
	public void setNoteID(int noteID) {
		NoteID = noteID;
	}
	public String getNoteContent() {
		return NoteContent;
	}
	public void setNoteContent(String noteContent) {
		NoteContent = noteContent;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public int getAccountID() {
		return AccountID;
	}
	public void setAccountID(int accountID) {
		AccountID = accountID;
	}
	public int getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}
	public int getStatusID() {
		return StatusID;
	}
	public void setStatusID(int statusID) {
		StatusID = statusID;
	}
	
	
	
	
}
