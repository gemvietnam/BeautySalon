package controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.BeautyDAO;
import dao.BeautyDAOImpl;
import models.Image;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FileUploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest res, HttpServletResponse response)
			throws ServletException, IOException {
	 
		// Commons file upload classes are specifically instantiated
		FileItemFactory factory = new DiskFileItemFactory();
	 
		ServletFileUpload upload = new ServletFileUpload(factory);
		ServletOutputStream out = null;
		
		System.out.println("Attempting to try to upload the file.");
	 
		try {
			// Parse the incoming HTTP request
			// Commons takes over incoming request at this point
			// Get an iterator for all the data that was sent
			List items = upload.parseRequest(res);
			Iterator iter = items.iterator();
	 
			// Set a response content type
			response.setContentType("text/html");
	 
			// Setup the output stream for the return XML data
			out = response.getOutputStream();
	 
			// Iterate through the incoming request data
			while (iter.hasNext()) {
				// Get the current item in the iteration
				FileItem item = (FileItem) iter.next();
	 
				// If the current item is an HTML form field
				if (item.isFormField()) {
					// Return an XML node with the field name and value
					out.println("this is a form data " + item.getFieldName() + "<br>");
	 
					// If the current item is file data
				} else {
					// Specify where on disk to write the file
					// Using a servlet init param to specify location on disk
					// Write the file data to disk
					// TODO: Place restrictions on upload data
					
					BeautyDAO beautyDAO = new BeautyDAOImpl();
					int lastId = beautyDAO.getLastImageId();
					int thisId = lastId + 1;
					
					String uploadPath = getServletContext().getRealPath("") + "uploads";
					System.out.println("UPLOAD PATH IS: " + uploadPath);
					String fileName = new File(item.getName()).getName();
					fileName = String.valueOf(thisId) + fileName;
					String filePath = uploadPath + File.separator + fileName;
					System.out.println("FILE PATH IS: " + filePath);
					File disk = new File(filePath);
					item.write(disk);
	 
					Image uploadedImage = new Image();
					uploadedImage.setPath(fileName);
					
					beautyDAO.addGalleryImage(uploadedImage);
					
					// Return an XML node with the file name and size (in bytes)
					//out.println(getServletContext().getRealPath("/WEB_INF"));
					out.println("this is a file with name: " + item.getName());
				}
			}
	 
			// Close off the response XML data and stream
	 
			out.close();
			// Rudimentary handling of any exceptions
			// TODO: Something useful if an error occurs
		} catch (FileUploadException fue) {
			fue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
	}

}
