package viewlayer;


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
import nta.model.DatabaseAccess;
import nta.model.TemplateProcessor;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String templateDir = "/WEB-INF/templates";
	Configuration cfg;
	private TemplateProcessor processor;
	private DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
	private SimpleHash root = new SimpleHash(db.build());
	private HttpSession session;
	DatabaseAccess dbaccess = new DatabaseAccess();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
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
		//this string is null if the signin button isn't pressed
		String signin = request.getParameter("signIn");
		
		if(signin != null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			boolean loginvalid = false;
			
			String welcome = "";
			Integer accessCount;


				//calls authentication method in Database Access
			loginvalid = dbaccess.authenticate(username,password);
			System.out.println(loginvalid);
	
			//For session tracking messages in the future. Welcome or welcome back, He will want this im sure			
			if(loginvalid == true) {
				session = request.getSession();
				synchronized(session) { 
					accessCount = (Integer)session.getAttribute("accessCount");
					if(accessCount == null) {
						accessCount = 0;
						welcome = "Welcome, for the first time";
					} else {
						welcome = "Welcome back, ";
						accessCount = accessCount + 1;
					}
					session.setAttribute("accessCount", accessCount);
				}
				
				root.put("username", username); 

//		Not necessary right now but will be helpful with session tracking				
//				root.put("welcome", welcome);
//				root.put("accessCount", accessCount); 
				
				loadSignedInPage(request,response);
				
			} else {
				loadFailedLoginPage(request,response); 
			}
		}
		
		
		
		
/*		PrintWriter out = response.getWriter();
		String dbusername = "root";
		String dbpassword = "given_password";
		String username = request.getParameter("username");
		String userpassword = request.getParameter("password");
		String select = "select * from users";
		
		if(signin != null)
		{
			if(searchForUser(username, userpassword, select, dbusername, dbpassword, "main", out))
			{
				Map<String, Object> root = new HashMap<>();
		        //root.put("results", );
				Template template;
				try {
					template = cfg.getTemplate("signedin.ftl");
					template.process(root, out);
				} catch (Exception e) {
					out.println(e.getMessage());
				}
			}
		}		
	}
	
	boolean searchForUser(String username, String password, String select, 
			String dbusername, String dbpassword, String database, PrintWriter out) 
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<User>userlist = new ArrayList<User>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//make connection to database
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+database+"?",
					dbusername,dbpassword);
			stmt = conn.createStatement();
			//execute statement on database
			rs = stmt.executeQuery(select);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			
			//while result set has values
			while(rs.next())
			{
				User u = new User();
				for(int i= 1; i<=rsmd.getColumnCount();i++)
				{
					if(rsmd.getColumnName(i).equals("username"))
					{
						u.username = rs.getString(i);
					}
					else if(rsmd.getColumnName(i).equals("password"))
					{
						u.password = rs.getString(i);
					}
				}
				userlist.add(u);
			}
			boolean userMatch = false;
			int matches = 0; //used to make sure there is only one match
			for(int i=0;i<userlist.size();i++)
			{
				if(userlist.get(i).username.equals(username))
				{
					if(userlist.get(i).password.equals(password))
					{
						if(matches == 0)
						{
							userMatch = true;
							matches++;
						}
						else
						{
							System.out.println("Multiple users matched!");
							return false;
						}
					}
				}
			}
			if(userMatch)
			{
				return true;
			}
		} 
		catch (Exception e) 
		{
			out.println("Invalid Search");
			return false;
		}
		finally {
			//close up all connections and such
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		    if(conn !=null)
		    {
		    	try {
		    		conn.close();
		    	}
		    	catch(SQLException sqlEx) {}
		    	conn = null;
		    }
		}
		return false;
	*/
	}

	private void loadFailedLoginPage(HttpServletRequest request, HttpServletResponse response) {
		//maybe have a failed login page here or something
		
	}

	private void loadSignedInPage(HttpServletRequest request, HttpServletResponse response) {
		String templatename = "signedin.ftl";
		processor.processTemplate(templatename,root,request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

/*	class User{
		public String username;
		public String password;
	}
	*/
}
