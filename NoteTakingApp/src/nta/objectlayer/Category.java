
package nta.objectlayer;

public class Category {
	private int CategoryID;
	private int AccountID;
	private String CategoryName;
	
	public Category() {
		this.CategoryID = 1;
		this.CategoryName = "General Notes";
	}

	public Category(int categoryID, int accountID, String categoryName) {
		this.CategoryID = categoryID;
		this.AccountID = accountID;
		this.CategoryName = categoryName;
	}

	public int getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}

	public int getAccountID() {
		return AccountID;
	}

	public void setAccountID(int accountID) {
		AccountID = accountID;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
}

