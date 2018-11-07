package nta.boundary;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import nta.logiclayer.CategoryLogic;
import nta.logiclayer.NotesLogic;
import nta.logiclayer.UserLogic;
import nta.objectlayer.User;
import nta.persistlayer.DatabaseAccess;
import nta.persistlayer.TemplateProcessor;
import nta.objectlayer.Category;
import nta.objectlayer.Notes;
/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	private String templateDir = "/WEB-INF/templates";
	Configuration cfg;
	private TemplateProcessor processor;
	private DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
	private SimpleHash root = new SimpleHash(db.build());
	private HttpSession session;
	DatabaseAccess dbaccess = new DatabaseAccess();
	boolean incorrectUsernameOrPassword = false;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException
    {
    	cfg = new Configuration(Configuration.VERSION_2_3_28);
    	// Specify the source where the template files come from. Here I set a
    	// plain directory for it, but non-file-system sources are possible too:
    	File file = new File(getServletContext().getRealPath(templateDir));
    	
    	try {
			cfg.setDirectoryForTemplateLoading(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	// Set the preferred charset template files are stored in. UTF-8 is
    	// a good choice in most applications:
    	cfg.setDefaultEncoding("UTF-8");

    	// Sets how errors will appear.
    	// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    	// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
    	cfg.setLogTemplateExceptions(false);

    	// Wrap unchecked exceptions thrown during template processing into TemplateException-s.
    	cfg.setWrapUncheckedExceptions(true);
    	
    	processor = new TemplateProcessor(templateDir, getServletContext());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	 
		//submit button for sign in
		String signin = request.getParameter("signIn");
		
		//submit button for direct to sign up page
		String signup = request.getParameter("signUp");
		
		//submit button for create user
		String createuser = request.getParameter("createUser");
		
		//fields for sign in
		String username = request.getParameter("username");
		String userpassword = request.getParameter("password");
		
		if(signin != null)
		{
			if(UserLogic.authenticateUser(username,userpassword))
			{
				int userid = UserLogic.getUserIdByUsername(username);
				System.out.println("User ID: "+userid);
				root.put("userid",userid);
				List<Notes> usernotes = NotesLogic.getNotesForAccountId(userid);
				root.put("usernotes",usernotes);
				List<Category> usercategories = CategoryLogic.getCategoriesForAccountId(userid);
				root.put("usercategories", usercategories);
				loadHomePage(request,response);
			}
			else
			{   
				incorrectUsernameOrPassword = true;
				root.put("incorrectUsernameOrPassword", incorrectUsernameOrPassword);
		        loadSignInPage(request,response);
			}
		}
		
		if(signup != null)
		{
			
			loadSignUpPage(request,response);
		}
		
		if(createuser != null)
		{
			incorrectUsernameOrPassword = false;
			
			//fields for sign up
			String newUsername = request.getParameter("newUsername");
			String newPassword= request.getParameter("newPassword");
			String email = request.getParameter("email");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			
			User user = new User(newUsername, newPassword, email, firstName, lastName);
			UserLogic.createUser(user);
			
			loadSignInPage(request,response);
		}
	}
	
	private void loadSignUpPage(HttpServletRequest request, HttpServletResponse response) {
		String templatename = "signuppage.ftl";
		processor.processTemplate(templatename,root,request,response);
	}

	private void loadSignInPage(HttpServletRequest request, HttpServletResponse response) {
		String templatename = "signinpage.ftl";
		root.put("incorrectUsernameOrPassword", incorrectUsernameOrPassword);
		processor.processTemplate(templatename,root,request,response);
	}

	private void loadHomePage(HttpServletRequest request, HttpServletResponse response) {
		incorrectUsernameOrPassword = false;
		String templatename = "home.ftl"; 
		processor.processTemplate(templatename,root,request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
<<<<<<< HEAD:NoteTakingApp/src/Login.java
	
	//old code
	{
//class User{
//		public String username;
//		public String password;
//	}
//	
//	boolean searchForUser(String username, String password, String select, 
//	String dbusername, String dbpassword, String database, PrintWriter out) 
//{
//Connection conn = null;
//Statement stmt = null;
//ResultSet rs = null;
//List<User>userlist = new ArrayList<User>();
//
//try {
//	Class.forName("com.mysql.jdbc.Driver");
//	//make connection to database
//	conn = DriverManager.getConnection("jdbc:mysql://localhost/"+database+"?",
//			dbusername,dbpassword);
//	stmt = conn.createStatement();
//	//execute statement on database
//	rs = stmt.executeQuery(select);
//	
//	ResultSetMetaData rsmd = rs.getMetaData();
//	
//	
//	//while result set has values
//	while(rs.next())
//	{
//		User u = new User();
//		for(int i= 1; i<=rsmd.getColumnCount();i++)
//		{
//			if(rsmd.getColumnName(i).equals("Username"))
//			{
//				u.username = rs.getString(i);
//			}
//			else if(rsmd.getColumnName(i).equals("Password"))
//			{
//				u.password = rs.getString(i);
//			}
//		}
//		userlist.add(u);
//	}
//	boolean userMatch = false;
//	int matches = 0; //used to make sure there is only one match
//	for(int i=0;i<userlist.size();i++)
//	{
//		if(userlist.get(i).username.equals(username))
//		{
//			if(userlist.get(i).password.equals(password))
//			{
//				if(matches == 0)
//				{
//					userMatch = true;
//					matches++;
//				}
//				else
//				{
//					System.out.println("Multiple users matched!");
//					return false;
//				}
//			}
//		}
//	}
//	if(userMatch)
//	{
//		return true;
//	}
//} 
//catch (Exception e) 
//{
//	out.println("Invalid Search");
//	return false;
//}
//finally {
//	//close up all connections and such
//	if (rs != null) {
//        try {
//            rs.close();
//        } catch (SQLException sqlEx) { } // ignore
//
//        rs = null;
//    }
//
//    if (stmt != null) {
//        try {
//            stmt.close();
//        } catch (SQLException sqlEx) { } // ignore
//
//        stmt = null;
//    }
//    if(conn !=null)
//    {
//    	try {
//    		conn.close();
//    	}
//    	catch(SQLException sqlEx) {}
//    	conn = null;
//    }
//}
//return false;
//}
	}
=======

>>>>>>> 932741c80b050096ea08d2005855f8c2dd5557b4:NoteTakingApp/src/nta/boundary/Login.java
}
