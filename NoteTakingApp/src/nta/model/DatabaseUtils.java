package nta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

	public void close(Statement st, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException sqlex) { }
		rs = null;
		
		try {
			if(st != null) {
				st.close();
			}
		} catch (SQLException sqlex) { }
		
	}
	
}
