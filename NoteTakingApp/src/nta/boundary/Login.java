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
	//private SimpleHash root = new SimpleHash(db.build());
	private HttpSession session;
	DatabaseAccess dbaccess = new DatabaseAccess();
	boolean incorrectUsernameOrPassword = false;
	static String usernameStorage;
	
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
				SimpleHash root = new SimpleHash(db.build());
				usernameStorage = username;
				int userid = UserLogic.getUserIdByUsername(username);
				System.out.println("User ID: "+userid);
				List<Notes> usernotes = NotesLogic.getNotesForAccountId(userid);
				List<Category> categories = CategoryLogic.getCategoriesForAccountId(userid);
				
				for(int i =0; i < categories.size(); i++) {
					System.out.println("Category:"+categories.get(i).getCategoryName());
					System.out.println("AccountID:"+categories.get(i).getAccountID());
					System.out.println("CategoryID:"+categories.get(i).getCategoryID());
				}
				
				System.out.println(categories.size());
				
				root.put("userid", userid);
				root.put("usernotes", usernotes);
				root.put("categories", categories);
				loadHomePage(request,response,root);
			}
			else
			{   
				SimpleHash root = new SimpleHash(db.build());
				incorrectUsernameOrPassword = true;
				root.put("incorrectUsernameOrPassword", incorrectUsernameOrPassword);
		        loadSignInPage(request,response,root);
			}
		}
		
		if(signup != null)
		{
			SimpleHash root = new SimpleHash(db.build());
			loadSignUpPage(request,response,root);
		}
		
		if(createuser != null)
		{
			SimpleHash root = new SimpleHash(db.build());
			incorrectUsernameOrPassword = false;
			
			//fields for sign up
			String newUsername = request.getParameter("newUsername");
			String newPassword= request.getParameter("newPassword");
			String email = request.getParameter("email");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			
			User user = new User(newUsername, newPassword, email, firstName, lastName);
			UserLogic.createUser(user);
			
			loadSignInPage(request,response,root);
		}
	}
	
	private void loadSignUpPage(HttpServletRequest request, HttpServletResponse response, SimpleHash root) {
		String templatename = "signuppage.ftl";
		processor.processTemplate(templatename,root,request,response);
	}

	private void loadSignInPage(HttpServletRequest request, HttpServletResponse response, SimpleHash root) {
		String templatename = "signinpage.ftl";
		root.put("incorrectUsernameOrPassword", incorrectUsernameOrPassword);
		processor.processTemplate(templatename,root,request,response);
	}

	private void loadHomePage(HttpServletRequest request, HttpServletResponse response, SimpleHash root) {
		incorrectUsernameOrPassword = false;
		String templatename = "home.ftl"; 
		root.put("user", usernameStorage);
		processor.processTemplate(templatename,root,request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
