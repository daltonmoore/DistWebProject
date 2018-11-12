
package nta.objectlayer;

public class Category {
	private int CategoryID;
	private int AccountID;
	private String categoryname;
	
	public Category() {
		this.CategoryID = 1;
		this.categoryname = "General Notes";
	}

	public Category(int categoryID, int accountID, String categoryname) {
		this.CategoryID = categoryID;
		this.AccountID = accountID;
		this.categoryname = categoryname;
	}

	public int getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(int categoryID) {
		this.CategoryID = categoryID;
	}

	public int getAccountID() {
		return AccountID;
	}

	public void setAccountID(int accountID) {
		this.AccountID = accountID;
	}

	public String getCategoryName() {
		return categoryname;
	}

	public void setCategoryName(String categoryname) {
		this.categoryname = categoryname;
	}
}

