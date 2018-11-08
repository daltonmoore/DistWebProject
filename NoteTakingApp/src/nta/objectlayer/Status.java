
package nta.objectlayer;

public class Status {
	private int StatusID;
	private String Description;
	
	public Status(int statusID, String description) {
		this.StatusID = statusID;
		this.Description = description;
	}

	public int getStatusID() {
		return StatusID;
	}

	public void setStatusID(int statusID) {
		StatusID = statusID;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
}

