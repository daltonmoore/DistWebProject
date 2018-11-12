package nta.boundary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateExceptionHandler;
import nta.logiclayer.CategoryLogic;
import nta.logiclayer.UserLogic;
import nta.objectlayer.Category;
import nta.persistlayer.TemplateProcessor;

/**
 * Servlet implementation class Navigation
 */
@WebServlet("/Navigate")
public class Navigate extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String templateDir = "/WEB-INF/templates";
	Configuration cfg;
	private TemplateProcessor processor;
	private DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
	private SimpleHash root = new SimpleHash(db.build());
	static String usernameStorage;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Navigate() {
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
		usernameStorage = request.getParameter("username");
		String goToCreateHeaderPage = request.getParameter("GoToCreateHeaderPage");
		String goToNotePage = request.getParameter("GoToNotePage");
		String submitHeaders = request.getParameter("submitHeaders");
		
		if(goToCreateHeaderPage != null || submitHeaders != null)
		{
			loadCreateHeaderPage(request, response);
		}
		else if(goToNotePage != null)
		{
			loadNotePage(request, response); 
		}
	}
	
	void loadCreateHeaderPage(HttpServletRequest request, HttpServletResponse response)
	{
		String templatename = "createheaderpage.ftl"; 
		root.put("username", usernameStorage);
		int userid = UserLogic.getUserIdByUsername(usernameStorage);
		List<Category> list =  CategoryLogic.getCategoriesForAccountId(userid);
		
		root.put("sequence", list);
		processor.processTemplate(templatename,root,request,response);
	}

	void loadNotePage(HttpServletRequest request, HttpServletResponse response)
	{
		String templatename = "home.ftl"; 
		root.put("user", usernameStorage);
		processor.processTemplate(templatename,root,request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}