
package nta.persistlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryPersist {

	public static ResultSet getCategoriesForAccount(int userid) {
		String query = "Select * from category where accountid="+userid;
		
		ResultSet results = DatabaseAccess.retrieve(query);
		
		
		return results;
	}
	
	public static int insertCategoriesForAccountId(int userid, String category)
	{
		String query = "insert into category values(0,"+userid+",'"+category+"')";
		int result = DatabaseAccess.create(query);
		
		return result;
	}

	public static int deleteCategoryForAccountId(int userid, String category)
	{
		String select = "select * from category where CategoryName='"+category+"'";
		ResultSet results = DatabaseAccess.retrieve(select);
		int categoryid = 0;
		try {
			System.out.println(select);
			if(results.next())
			{
				categoryid = Integer.parseInt(results.getString(1));
				System.out.println(results.getString(1));
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			return -1;
		}
		String query = "delete from category where CategoryID = "+categoryid;
		int result = DatabaseAccess.delete(query);
		if(result>0)
			return 1;
		else
			return -1;
	}
}

