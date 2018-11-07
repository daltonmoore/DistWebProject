package nta.logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nta.objectlayer.Category;
import nta.persistlayer.CategoryPersist;

public class CategoryLogic {

	public static List<Category> getCategoriesForAccountId(int userid) {
		List<Category> categories = new ArrayList<Category>();
		
		ResultSet results = CategoryPersist.getCategoriesForAccount(userid);
 		
		try {
			while(results.next()) {
				Category category = new Category();
				category.setCategoryID(results.getInt(1));
				category.setAccountID(results.getInt(2));
				category.setCategoryName(results.getString(3));
				categories.add(category);
				
				System.out.println("List created. Returning category list of size: " + categories.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
}
