<<<<<<< HEAD:NoteTakingApp/src/nta/persistlayer/DatabaseUtils.java
package nta.persistlayer;

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
=======
package nta.persistlayer;

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
>>>>>>> origin/master:NoteTakingApp/src/nta/persistlayer/DatabaseUtils.java
