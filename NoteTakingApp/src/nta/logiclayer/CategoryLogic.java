package nta.logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nta.objectlayer.Category;
import nta.persistlayer.CategoryPersist;
import nta.persistlayer.DatabaseAccess;

public class CategoryLogic {

	public static List getCategoriesForAccountId(int userid) {
		List<Category> categories = new ArrayList<Category>();
		
		ResultSet results = CategoryPersist.getCategoriesForAccount(userid);
 		
		try {
			while(results.next()) {
				Category category = new Category();
				category.setCategoryID(results.getInt(1));
				category.setAccountID(results.getInt(2));
				System.out.println("account ID from category: " + category.getAccountID());
				category.setCategoryName(results.getString(3));
				System.out.println("CategoryName : " + category.getCategoryName());
				categories.add(category);
			}
			System.out.println("List created. Returning category list of size: " + categories.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatabaseAccess.disconnect();
		
		return categories;
	}
	
	public static int insertCategoriesForAccountId(int userid, String category)
	{
		return CategoryPersist.insertCategoriesForAccountId(userid, category);
	}

	public static int deleteCategoryForAccountId(int userid, String category)
	{
		return CategoryPersist.deleteCategoryForAccountId(userid, category);
	}
}

