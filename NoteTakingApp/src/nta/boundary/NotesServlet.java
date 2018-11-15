package nta.boundary;



import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateExceptionHandler;
import nta.logiclayer.NotesLogic;
import nta.objectlayer.Notes;
import nta.persistlayer.TemplateProcessor;

/**
 * Servlet implementation class Notes
 */
@WebServlet("/NotesServlet")
public class NotesServlet extends HttpServlet 
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
    public NotesServlet() {
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//newnote json object sent from ajax 
		String newnote = request.getParameter("newnote");
		
		//update note in db
		String updatenote = request.getParameter("updatenote");
		
		//Note to be deleted
		String deleteid = request.getParameter("deleteId");
		
		if(newnote!=null) {
			Gson gson = new Gson();
			Notes note = gson.fromJson(newnote, Notes.class);
			System.out.println("Adding new note.... \nNote title: " + note.getNoteTitle() + " | Note content: "+ note.getNoteContent());
			int NoteID = NotesLogic.createNewNote(note);
			note.setNoteID(NoteID);
			
			String json = gson.toJson(note);
			System.out.println(json);
			PrintWriter writer = response.getWriter();
			writer.println(json);
		}
		
		if(updatenote!=null) {
			Gson gson = new Gson();
			Notes note = gson.fromJson(updatenote, Notes.class);
			System.out.println(updatenote);
			System.out.println("Updating note.... \nNote title: " + note.getNoteTitle() + " | Note content: "+ note.getNoteContent());
			
			int numRowsAffected = NotesLogic.updateNote(note);
			PrintWriter writer = response.getWriter();
			response.setContentType("html/text");
			
			if(numRowsAffected > 0) {
				writer.write("Successfully Updated "+numRowsAffected+" rows.");
			}else {
				writer.write("An error occured when updating.");
			}
		}
		
		if(deleteid!=null) {
			int numRowsAffected = NotesLogic.deleteNote(deleteid);
			
			PrintWriter writer = response.getWriter();
			response.setContentType("html/text");
			
			if(numRowsAffected > 0) {
				writer.write("Successfully Deleted "+numRowsAffected+" rows.");
			}else {
				writer.write("An error occured when deleting.");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
