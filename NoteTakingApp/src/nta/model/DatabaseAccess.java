package nta.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//use this class to run queries and such as well as get connection
public class DatabaseAccess {
	DatabaseUtils dbutil = new DatabaseUtils();
	
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DatabaseConfiguration.mysqlURL, DatabaseConfiguration.username, DatabaseConfiguration.password);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public boolean authenticate(String username,String password) {
		boolean valid = false;
		ResultSet rs = null;
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			String sql =  "SELECT username, password FROM users WHERE username='"+username+"';";
			rs = st.executeQuery(sql);
			
			String rsUsername = "";
			String rsPass="";
			while (rs.next()) {
				rsUsername = rs.getString("username");
				rsPass = rs.getString("password");
				System.out.println(username + " " + password+ " " + rsUsername + " " + rsPass);
			}
			if(rsPass.equals(password) && rsUsername.equals(username)) {
				valid = true;
			}else { 
				valid=false;
			}
		//close statement and result set	
			dbutil.close(st, rs);
		
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
		
		return valid;
		
	}
	
	
}
