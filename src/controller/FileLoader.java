package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;
import org.apache.jasper.el.ELContextWrapper;
 
/**
 * Servlet implementation class FileLoader
 */

@WebServlet("/FileLoader")
public class FileLoader extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private File file;
	//private File uploadDir = new File("/Users/Dora/Tuk");
	//private File tmp = new File("/Users/Dora/Tam");
	//private File uploadDir = new File("/Users/Dora/Documents/EclipseWorkspace/NewLibraryV3Web/WebContent/resources");	
	private File uploadDir;
	private String filePath;
	private String myWebDir;
	private int maxFileSize = 256 * 1024;
	private int maxMemSize = 4 * 1024;
	String success = new String();
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileLoader() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init( )
    {
        // Get the file storage location from web.xml
        filePath = getServletContext().getInitParameter("file-upload"); 
        myWebDir = getServletContext().getRealPath("/");
        System.out.println("MY WEB DIRECTORY " + myWebDir);
        uploadDir = new File( myWebDir + "/uploads");
        System.out.println("UPLOAD DIRECTORY" + uploadDir);
        System.out.println("FILE PATH" + filePath);
    }   		
        		   
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		throw new ServletException("GET method used with " +
                getClass( ).getName( ) + ": POST method required.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
//		PrintWriter out = response.getWriter();
//		response.setContentType("text/html");
		
		// Check for a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart)	
		{
//			out.println("Nothing to upload");
	    	   	return; 
	    	}
	     
	    // Configure the environment
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    // max memory size
	    factory.setSizeThreshold(maxMemSize);
	    // location for storage larger than maxMemSize 
	    factory.setRepository(uploadDir);

	    // Create a new file upload handler
	    //FileItemFactory itemFactory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    // max file size 
	    upload.setSizeMax(maxFileSize);
	    
	      // Parse the request to get file items.
	      try
	      {
	    		List<FileItem>  items = upload.parseRequest(request);
	    		for(FileItem item:items)
	    		{ 
	    			//if ( !item.isFormField () )	
	    	         {
	    	            // Get file parameters
	    	            String fieldName = item.getFieldName();
	    	            String fileName = item.getName();
	    	            String contentType = item.getContentType();
	    	            boolean isInMemory = item.isInMemory();
	    	            long sizeInBytes = item.getSize();
	    	            System.out.println(fileName);
	    	            
	    	            // Write the file	    	            
	    	            //file = File.createTempFile("img",".png",uploadDir);
	    	            file = new File(uploadDir,fileName);
	    	            item.write(file);
	    	            success = "File created successfully";
	    	            request.setAttribute("title", success);
	    	            request.getRequestDispatcher("jsp/admin/index.jsp").include(request, response);
	    	            
//	    	            getServletContext().getRequestDispatcher("/jsp/admin/fileupload.jsp").forward(request, response);
	    	            System.out.println("File created successfully");
	    	         }
	    			}
	      	}
	      	catch(FileUploadException ex) 
	    		{
	   	      System.out.println("Upload error " + ex);
	    		}
	      	catch(Exception ex) 
  			{
	      		System.out.println("Can not save " + ex);
  			}
	  }
}
