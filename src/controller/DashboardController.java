package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.BeautyDAO;
import dao.BeautyDAOImpl;
import models.Booking;
import models.Category;
import models.Employee;
import models.Image;
import models.Page;
import models.Service;
import models.Setting;
import models.User;


/**
* <h1>Prettier CMS</h1>
* Prettier CMS is content management system for small enterprises. 
* It allows to manage all the website data with booking functionality.
* <p>
* Application consists of dashboard panel and public website.
* In dashboard panel, administrators can manage business data like bookings, employees, treatments and contents of the website.
* 
*
* @author  Jagoda Przybyla
* @version 1.0
* @since   2016-06-20 
*/

@WebServlet("/admin")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private File file;
	private File uploadDir;
	private String filePath;
	private String myWebDir;
	private int maxFileSize = 256 * 1024;
	private int maxMemSize = 4 * 1024;
	String success = new String();
	
    public DashboardController() {
        super();
    }
    
    public void init( )
    {
        filePath = getServletContext().getInitParameter("file-upload"); 
        myWebDir = getServletContext().getRealPath("/");
        uploadDir = new File( myWebDir + "/uploads");
    }   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	   * DashboardController doPost method contains all the routing for the application. 
	   * Routing is executed using switch case by getting page parameter. 
	   * It controlls all the operations performed in the dashboard.
	   */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("page");
		action = (action == null) ? "default" : action;
		String url = "/jsp/admin/index.jsp";	
		
		System.out.println("Check if logged in");
		HttpSession session = request.getSession();
		System.out.println("The session login content: " + session.getAttribute("isLoggedIn"));
		
		Boolean isLoggedIn = false;
		
		if (session.getAttribute("isLoggedIn") != null) {
			isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
		}
		
		if (session.getAttribute("user") == null) {
			isLoggedIn = false;
		}
		
		if (isLoggedIn == false) {
			System.out.println("The user is not logged in, redirect to login screen");
			request.setAttribute("isError", false);
			url = "/jsp/login.jsp";
		}
		else {
			System.out.println("The user is logged in");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);
			switch (action) {
			case "categories":
				String type = (String) request.getParameter("type");
				System.out.println("The type of category action is: " + type);
				if (type != null) {
					switch (type) {
					case "add":
						System.out.println("Add category");
						addCategoryWithImage(request, response);
						break;
					case "update":
						System.out.println("Update category");
						updateCategoryWithImage(request, response);
						break;
					}
				}
				url = "/jsp/admin/categories.jsp";
				getCategories(request, response);
				break;
			case "searchCategories":
				url = "/jsp/admin/categories.jsp";
				searchCategories(request, response);
				break;
			case "addCategory":
				url = "/jsp/admin/add-category.jsp";
				break;
			case "editCategory":
				url = "/jsp/admin/edit-category.jsp";
				getCategoryById(request, response);
				break;
			case "editProfile":
				url = "/jsp/admin/edit-profile.jsp";
				getUserById(request, response);
				String update = request.getParameter("update");
				if (update != null && update.equals("true")) {
					System.out.println("Updating the user");
					updateUser(request, response);
					getUserById(request, response);
				}
				break;
			case "delete":
				System.out.println("Wer are deleting the record now");
				deleteRecord(request, response);
				String page = request.getParameter("table");
				url = "/jsp/admin/" + page.toLowerCase() + ".jsp";
				switch (page) {
				case "Services":
					getServices(request, response);
					break;
				case "Categories":
					getCategories(request, response);
					break;
				case "Employees":
					getEmployees(request, response);
					break;
				}
				break;	
			case "deleteEmployee":
				url = "/jsp/admin/employees.jsp";
				int employeeId = Integer.parseInt(request.getParameter("id"));
				beautyDAO.deleteEmployee(employeeId);
				getEmployees(request, response);
				break;
			case "treatments":
				String typeAction = (String) request.getParameter("type");
				System.out.println("The type of service action is: " + typeAction);
				if (typeAction != null) {
					switch (typeAction) {
					case "add":
						System.out.println("Add service");
						addService(request, response);
						break;
					case "update":
						System.out.println("Update service");
						updateService(request, response);
						break;
					}
				}
				url = "/jsp/admin/services.jsp";
				getServices(request, response);
				break;
			case "searchTreatments":
				url = "/jsp/admin/services.jsp";
				searchServices(request, response);
				break;
			case "addTreatment":
				url = "/jsp/admin/add-service.jsp";
				getCategories(request, response);
				break;
			case "editTreatment":
				url = "/jsp/admin/edit-service.jsp";
				getServiceById(request, response);
				getCategories(request, response);
				break;
			case "deleteService":
				int serviceId = Integer.parseInt(request.getParameter("id"));
				beautyDAO.deleteService(serviceId);
				url = "/jsp/admin/services.jsp";
				getServices(request, response);
				break;
			case "bookings":
				url = "/jsp/admin/bookings.jsp";
				getBookings(request, response);
				break;
			case "cancelBooking":
				int bookingId = Integer.parseInt(request.getParameter("id"));
				beautyDAO.cancelBooking(bookingId);
				url = "/jsp/admin/bookings.jsp";
				getBookings(request, response);
				break;
			case "pages":
				String pageType = (String) request.getParameter("type");
				System.out.println("The type of category action is: " + pageType);
				if (pageType != null) {
					switch (pageType) {
					case "add":
						System.out.println("Add page");
						addPage(request, response);
						break;
					case "update":
						System.out.println("Update page");
						updatePage(request, response);
						break;
					case "updateMenu":
						System.out.println("Update menu");
						updateMenu(request, response);
						break;
					}
				}
				url = "/jsp/admin/pages.jsp";
				getPages(request, response);
				break;
			case "addPage":
				url = "/jsp/admin/add-page.jsp";
				break;
			case "editPage":
				url = "/jsp/admin/edit-page.jsp";
				getPageById(request, response);
				break;
			case "editHomePage":
				url = "/jsp/admin/edit-homepage.jsp";
//				getHomePage(request, response);
				break;
			case "deletePage":
				url = "/jsp/admin/pages.jsp";
				deletePage(request, response);
				getPages(request, response);
				break;
			case "employees":
				String typeEmployee = (String) request.getParameter("type");
				System.out.println("The type of service action is: " + typeEmployee);
				if (typeEmployee != null) {
					switch (typeEmployee) {
					case "add":
						System.out.println("Add employee");
						addEmployeeWithImage(request, response);
						break;
					case "update":
						System.out.println("Update employee");
						updateEmployeeWithImage(request, response);
						break;
					}
				}
				url = "/jsp/admin/employees.jsp";
				getEmployees(request, response);
				break;
			case "searchEmployees":
				url = "/jsp/admin/employees.jsp";
				searchEmployees(request, response);
				break;
			case "addEmployee":
				getServices(request, response);
				url = "/jsp/admin/add-employee.jsp";
				break;
			case "editEmployee":
				url = "/jsp/admin/edit-employee.jsp";
				getEmployeeById(request, response);
				getServices(request, response);
				getServicesByEmployeeId(request, response);
				break;
			case "settings":
				url = "/jsp/admin/settings.jsp";
				getSettings(request, response);
				break;
			case "translator":
				url = "/jsp/admin/translator.jsp";
				break;
			case "saveSettings":
				url = "/jsp/admin/settings.jsp";
				System.out.println("Attempting to save settings");
//				saveSettings(request, response);
				saveSettingsWithImage(request, response);
				System.out.println("Getting the settings back");
				getSettings(request, response);
				break;
			case "schedule":
				url = "/jsp/admin/schedule.jsp";
				getEmployees(request, response);
				if (request.getParameter("employeeId") != null) {
					getSchedule(request, response);
					url = "/jsp/admin/render-schedule.jsp";
				}
				break;
			case "images":
				url = "/jsp/admin/fileupload.jsp";
				List<Image> images = beautyDAO.getImages();
				request.setAttribute("images", images);
				String akcja = (String) request.getParameter("action");
				String delete = (String) request.getParameter("delete");
				System.out.println("ackaj is: " + akcja);
				
				if (akcja != null) {
					System.out.println("Add image to database");
					addImageToDb(request, response);
					System.out.println("IMAGE ADDED");
					List<Image> imagesUpdated = beautyDAO.getImages();
					request.setAttribute("images", imagesUpdated);
				}
				
				if (delete != null) {
					System.out.println("Delete the image from database");
					deleteImage(request, response);
				}
				
				break;
			default:
				System.out.println("Getting dashboard stats");
				getDashboardStats(request, response);
				getCategories(request, response);
				break;
			}		
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
	/**
	   * GetCategories method calls data access object and fetches list of categories from the database.
	   * Afterwards sends it to website by setting request attribute.
	   */
	private void getCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("Trying to get the categories");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Category> categories = beautyDAO.getCategories();
			request.setAttribute("categories", categories);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	   * GetSchedule method calls data access object and fetches list of bookings for employee from the database.
	   * Afterwards sends it to website by setting request attribute.
	   */
	private void getSchedule(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("Trying to get the schedule");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			List<Booking> bookings = beautyDAO.getBookings(employeeId);
			request.setAttribute("bookings", bookings);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	   * GetDashboardStats method calls data access object and fetches total statistics from the database.
	   * Afterwards sends it to website by setting request attribute.
	   */
	private void getDashboardStats(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			int totalProfit = beautyDAO.getTotalProfit();
			request.setAttribute("totalProfit", totalProfit);
			int totalCategories = beautyDAO.getTotal("Categories");
			request.setAttribute("totalCategories", totalCategories);
			int totalEmployees = beautyDAO.getTotal("Employees");
			request.setAttribute("totalEmployees", totalEmployees);
			int totalServices = beautyDAO.getTotal("Services");
			request.setAttribute("totalServices", totalServices);
			int totalBookings = beautyDAO.getTotal("Bookings");
			request.setAttribute("totalBookings", totalBookings);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	/**
	   * AddImageToDb method writed the file on the server and adds the record to the database images table.
	   */
	private void addImageToDb(HttpServletRequest request,
			HttpServletResponse response) {
		
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
	    	            // Get last image id
	    	        	BeautyDAO beautyDAO = new BeautyDAOImpl();
	    	        	int lastImageId = beautyDAO.getLastImageId();
	    	        	
	    	        	// Get file parameters
	    	            String fieldName = item.getFieldName();
	    	            String fileName = String.valueOf(lastImageId) + item.getName();
	    	            String contentType = item.getContentType();
	    	            boolean isInMemory = item.isInMemory();
	    	            long sizeInBytes = item.getSize();
	    	            System.out.println(fileName);
	    	            System.out.println("Size of the file is: "  + sizeInBytes);
	    	            System.out.println("Maximum file size is: " + maxFileSize);
	    	            
	    	            // Write the file	    	            
	    	            file = new File(uploadDir,fileName);
	    	            item.write(file);
	    	            success = "File created successfully";
	    	            request.setAttribute("title", success);
	    	            
	    	            Image image = new Image();
	    	            image.setPath(fileName);
	    	            beautyDAO.addGalleryImage(image);
	    	            
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
	
	/**
	   * AddEmployeeWithImage method adds the employee to the database using data access object and writes uploaded file on the server.
	   */
	private void addEmployeeWithImage(HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart) {
			System.out.println("Nothing to upload");
	    	return; 
	    }
	     
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    factory.setSizeThreshold(maxMemSize);
	    factory.setRepository(uploadDir);
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    upload.setSizeMax(maxFileSize);
	    
	    String firstName = "";
	    String lastName = "";
	    String email = "";
	    String title = "";
	    String description = "";
	    String profilePicture = "";
	    String fileName = "";
	    List<String> ids = new ArrayList<String>();
	    
	      try
	      {
	    		List<FileItem>  items = upload.parseRequest(request);
	    		for(FileItem item:items)
	    		{ 
	    			if (item.isFormField()) {
	    				System.out.println("Getting value of form field");
	    				String name = item.getFieldName();//text1
	    			    String value = item.getString();
	    			    switch(name) {
	    			    case "firstName":
	    			    	firstName = value;
	    			    	break;
	    			    case "lastName":
	    			    	lastName = value;
	    			    	break;
	    			    case "email":
	    			    	email = value;
	    			    	break;
	    			    case "title":
	    			    	title = value;
	    			    	break;
	    			    case "description":
	    			    	description = value;
	    			    	break;
	    			    case "service":
	    			    	ids.add(value);
	    			    	break;
	    			    }
	    				
	    				System.out.println("Firstname: " + firstName);
	    			}
	    			
	    			else {
	    	        	BeautyDAO beautyDAO = new BeautyDAOImpl();
	    	        	int lastImageId = beautyDAO.getLastImageId();

	    	            String fieldName = item.getFieldName();
	    	            fileName = String.valueOf(lastImageId) + item.getName();
	    	            String contentType = item.getContentType();
	    	            boolean isInMemory = item.isInMemory();
	    	            long sizeInBytes = item.getSize();
	    	            System.out.println(fileName);
	    	            
	    	            uploadDir = new File( myWebDir + "/uploads/employees");
	    	            
	    	            file = new File(uploadDir,fileName);
	    	            item.write(file);
	    	            
	    	            System.out.println("File created successfully");
	    	         }
	    		}
	    		
	    		Employee e = new Employee();
				e.setFirstName(firstName);
				e.setLastName(lastName);
				e.setEmail(email);
				e.setTitle(title);
				e.setDescription(description);
				e.setProfilePicture(fileName);
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				e.setAuthor(user.getId());
				Timestamp created = new Timestamp(System.currentTimeMillis());
				e.setLastUpdated(created);
				
				BeautyDAO beautyDAO = new BeautyDAOImpl();
				beautyDAO.addEmployee(e);
				
				System.out.println("New created employee ID is: " + e.getId());
				int employeeId = e.getId();
				
			    for (String id:ids) {
			    	System.out.println("Selected ID is: " + id);
			    	int serviceId = Integer.parseInt(id);
					beautyDAO.addEmployeeService(employeeId, serviceId);
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
	
	/**
	   * UpdateEmployeeWithImage allows to update employee image and database employee record.
	   */
	
	private void updateEmployeeWithImage(HttpServletRequest request,
				HttpServletResponse response) {
			
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			
			if (!isMultipart) {
				System.out.println("Nothing to upload");
		    	return; 
		    }
		     
			DiskFileItemFactory factory = new DiskFileItemFactory();
		    factory.setSizeThreshold(maxMemSize);
		    factory.setRepository(uploadDir);
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    upload.setSizeMax(maxFileSize);
		    
		    String firstName = "";
		    String lastName = "";
		    String email = "";
		    String title = "";
		    String description = "";
		    String fileName = "";
		    String existingImagePath = null;
		    int id = 0;
		    List<String> ids = new ArrayList<String>();
		    
		      try
		      {
		    		List<FileItem>  items = upload.parseRequest(request);
		    		for(FileItem item:items)
		    		{ 
		    			if (item.isFormField()) {
		    				String name = item.getFieldName();
		    			    String value = item.getString();
		    			    switch(name) {
		    			    case "firstName":
		    			    	firstName = value;
		    			    	break;
		    			    case "lastName":
		    			    	lastName = value;
		    			    	break;
		    			    case "email":
		    			    	email = value;
		    			    	break;
		    			    case "title":
		    			    	title = value;
		    			    	break;
		    			    case "description":
		    			    	description = value;
		    			    	break;
		    			    case "service":
		    			    	ids.add(value);
		    			    	break;
		    			    case "id":
		    			    	System.out.println("The field is id, with value: " + value);
		    			    	id = Integer.parseInt(value);
		    			    	System.out.println("Now our id is: " + id);
		    			    	break;
		    			    case "existingImagePath":
		    			    	System.out.println("Taking existing image path, " + value);
		    			    	existingImagePath = value;
		    			    	break;
		    			    }
		    				
		    			}
		    			
		    			else {
		    	        	BeautyDAO beautyDAO = new BeautyDAOImpl();
		    	        	int lastImageId = beautyDAO.getLastImageId();
	
		    	            String fieldName = item.getFieldName();
		    	            fileName = String.valueOf(lastImageId) + item.getName();
		    	            String contentType = item.getContentType();
		    	            boolean isInMemory = item.isInMemory();
		    	            long sizeInBytes = item.getSize();
		    	            System.out.println(fileName);
		    	            
		    	            uploadDir = new File( myWebDir + "/uploads/employees");
		    	            
		    	            file = new File(uploadDir,fileName);
		    	            item.write(file);
		    	            
		    	            System.out.println("File created successfully");
		    	         }
		    		}
		    		
		    		Employee e = new Employee();
					e.setFirstName(firstName);
					e.setLastName(lastName);
					e.setEmail(email);
					e.setTitle(title);
					e.setDescription(description);
					e.setId(id);
					HttpSession session = request.getSession();
					User user = (User) session.getAttribute("user");
					e.setAuthor(user.getId());
					Timestamp created = new Timestamp(System.currentTimeMillis());
					e.setLastUpdated(created);
					
					String profilePicture;
					
					System.out.println("exidting path is: '" + existingImagePath + "'");
					if (!existingImagePath.equals("newFile")) {
						profilePicture = existingImagePath;
					}
					else {
						profilePicture = fileName;
					}
					
					System.out.println("PROFILE PICTURE IS: " + profilePicture);
					
					e.setProfilePicture(profilePicture);
					System.out.println("Set profile picture: " + e.getProfilePicture());
	
	//				if (existingImagePath != null) {
	//					e.setProfilePicture(existingImagePath);
	//				}
					
					System.out.println("Get profile picture: " + e.getProfilePicture());
					
					System.out.println("existingImagePath is: " + existingImagePath + " filename is: " + fileName);
					
					BeautyDAO beautyDAO = new BeautyDAOImpl();
					System.out.println("Updating employee in db, " + e.getProfilePicture());
					beautyDAO.updateEmployee(e);
					
					int employeeId = e.getId();
					System.out.println("Removing connection to services, employeeId is: " + employeeId);
					beautyDAO.deleteEmployeeServiceByEmployeeId(employeeId);
					
				    for (String item:ids) {
				    	System.out.println("Trying to add: service id " + item + ", employee id " + employeeId);
				    	int serviceId = Integer.parseInt(item);
						beautyDAO.addEmployeeService(employeeId, serviceId);
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

	
	/**
	   * DeleteImage method deleted the record from database images table.
	   */
	
	private void deleteImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String idString = request.getParameter("imageId");
			int id = Integer.parseInt(idString);
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.deleteImage(id);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	   * getCategoryById method fetches category data from the database using category id.
	   */
	private void getCategoryById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int categoryId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Category category = beautyDAO.getCategoryById(categoryId);
			request.setAttribute("category", category);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	   * getUserById method fetches user data from the database using user id.
	   */
	private void getUserById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			int id = user.getId();
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			User userData = beautyDAO.getUserById(id);
			request.setAttribute("userData", userData);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	/**
	   * updateUser method allows to update user data in database.
	   */
	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			User u = new User();
			u.setFirstName(request.getParameter("firstName"));
			u.setLastName(request.getParameter("lastName"));
			u.setEmail(request.getParameter("email"));
			u.setId(Integer.parseInt(request.getParameter("id")));
			
			String password = request.getParameter("password");
			System.out.println("Password is: " + password);
			
			if (password != null) {
				u.setPassword(password);
			}
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.updateUser(u);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	   * getServiceById method fetches service data from the database using service id.
	   */
	private void getServiceById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int serviceId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Service service = beautyDAO.getServiceById(serviceId);
			request.setAttribute("service", service);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	/**
	   * getServices method fetches list of services from the database.
	   */
	private void getServices(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Service> services = beautyDAO.getServices();
			request.setAttribute("services", services);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void searchServices(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String keyword = request.getParameter("keyword");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Service> services = beautyDAO.searchServices(keyword);
			request.setAttribute("services", services);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void searchCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String keyword = request.getParameter("keyword");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Category> categories = beautyDAO.searchCategories(keyword);
			request.setAttribute("categories", categories);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void searchEmployees(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String keyword = request.getParameter("keyword");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Employee> employees = beautyDAO.searchEmployees(keyword);
			request.setAttribute("employees", employees);

		} catch (Exception e) {
			System.out.println(e);
		}
	}		
	
	/**
	   * getSettings method fetches all the settings from the database.
	   */
	private void getSettings(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Setting setting = (Setting) beautyDAO.getSettings();
			request.setAttribute("settings", setting);
			getServletContext().setAttribute("settings", setting);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
//	private void saveSettings(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		try {
//			
//			Setting s = new Setting();
//			s.setSiteTitle(request.getParameter("siteTitle"));
//			s.setSiteDescription(request.getParameter("siteDescription"));
//			s.setCompanyName(request.getParameter("companyName"));
//			s.setAddress(request.getParameter("address"));
//			s.setVatNumber(request.getParameter("vatNumber"));
//			s.setPhone(request.getParameter("phone"));
//			s.setEmail(request.getParameter("email"));
//			s.setFacebook(request.getParameter("facebook"));
//			s.setTwitter(request.getParameter("twitter"));
//			s.setGooglePlus(request.getParameter("googlePlus"));
//			s.setInstagram(request.getParameter("instagram"));
//			s.setPinterest(request.getParameter("pinterest"));
//
//			BeautyDAO beautyDAO = new BeautyDAOImpl();
//			beautyDAO.saveSettings(s);
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}		
	
	
	/**
	   * saveSettingsWithImage method adds all the settings to the database and writes images on server.
	   */
	private void saveSettingsWithImage(HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			System.out.println("Nothing to upload");
	    	return; 
	    }
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    factory.setSizeThreshold(maxMemSize);
	    factory.setRepository(uploadDir);
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    upload.setSizeMax(maxFileSize);
	    
	    String siteTitle = "";
	    String siteDescription = "";
	    String logo = "";
	    String favicon = "";
	    String companyName = "";
	    String vatNumber = "";
	    String address = "";
	    String phone = "";
	    String email = "";
	    String facebook = "";
	    String twitter = "";
	    String instagram = "";
	    String googlePlus = "";
	    String pinterest = "";
	    String fileName = "";
	    
	      try
	      {
	    		List<FileItem>  items = upload.parseRequest(request);
	    		for(FileItem item:items)
	    		{ 
	    			if (item.isFormField()) {
	    				System.out.println("Getting value of form field");
	    				String name = item.getFieldName();
	    			    String value = item.getString();
	    			    switch(name) {
	    			    case "siteTitle":
	    			    	siteTitle = value;
	    			    	break;
	    			    case "siteDescription":
	    			    	siteDescription = value;
	    			    	break;
	    			    case "companyName":
	    			    	companyName = value;
	    			    	break;
	    			    case "vatNumber":
	    			    	vatNumber = value;
	    			    	break;
	    			    case "address":
	    			    	address = value;
	    			    	break;
	    			    case "phone":
	    			    	phone = value;
	    			    	break;
	    			    case "email":
	    			    	email = value;
	    			    	break;
	    			    case "facebook":
	    			    	facebook = value;
	    			    	break;
	    			    case "twitter":
	    			    	twitter = value;
	    			    	break;
	    			    case "instagram":
	    			    	instagram = value;
	    			    	break;
	    			    case "googlePlus":
	    			    	googlePlus = value;
	    			    	break;
	    			    case "pinterest":
	    			    	pinterest = value;
	    			    	break;
	    			    }
	    			}
	    			
	    			else {
	    	            String fieldName = item.getFieldName();
	    	            fileName = item.getName();
	    	            if (fieldName.equals("logo")) {
	    	            	logo = fileName;
	    	            }
	    	            else if (fieldName.equals("favicon")) {
	    	            	favicon = fileName;
	    	            }
	    	            
	    	            String contentType = item.getContentType();
	    	            boolean isInMemory = item.isInMemory();
	    	            long sizeInBytes = item.getSize();
	    	            System.out.println(fileName);
	    	            uploadDir = new File( myWebDir + "/uploads/settings");
	    	            file = new File(uploadDir,fileName);
	    	            item.write(file);
	    	            System.out.println("File created successfully");
	    	         }
	    		}
	    		
	    		Setting s = new Setting();
				s.setSiteTitle(siteTitle);
				s.setSiteDescription(siteDescription);
				s.setCompanyName(companyName);
				s.setAddress(address);
				s.setVatNumber(vatNumber);
				s.setPhone(phone);
				s.setEmail(email);
				s.setFacebook(facebook);
				s.setTwitter(twitter);
				s.setGooglePlus(googlePlus);
				s.setInstagram(instagram);
				s.setPinterest(pinterest);
				s.setLogo(logo);
				s.setFavicon(favicon);

				BeautyDAO beautyDAO = new BeautyDAOImpl();
				beautyDAO.saveSettings(s);
				System.out.println("Settings saved!");
	    		
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
	
	
	
	private void getServicesByCategoryId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int categoryId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Service> services = beautyDAO.getServicesByCategoryId(categoryId);
			request.setAttribute("services", services);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void getServicesByEmployeeId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int employeeId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Integer> serviceIds = beautyDAO.getServicesByEmployeeId(employeeId);
			request.setAttribute("serviceIds", serviceIds);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void getEmployees(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Employee> employees = beautyDAO.getEmployees();
			request.setAttribute("employees", employees);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	

	
	private void getBookings(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Booking> bookings = beautyDAO.getBookings();
			request.setAttribute("bookings", bookings);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void getPages(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Page> pages = beautyDAO.getPages();
			request.setAttribute("pages", pages);
			
			List<String[]> menu = beautyDAO.getMenu();
			request.setAttribute("menu", menu);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void getEmployeeById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int employeeId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Employee employee = beautyDAO.getEmployeeById(employeeId);
			request.setAttribute("employee", employee);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Category c = new Category();
			c.setName(request.getParameter("name"));
			c.setDescription(request.getParameter("description"));
			c.setPicture(request.getParameter("picture"));
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			c.setAuthor(user.getId());
			
			System.out.println("Trying to add the category");
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.addCategory(c);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void updateCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Category c = new Category();
			c.setName(request.getParameter("name"));
			c.setDescription(request.getParameter("description"));
			c.setPicture(request.getParameter("picture"));
			c.setId(Integer.parseInt(request.getParameter("id")));
			Timestamp created = new Timestamp(System.currentTimeMillis());
			c.setLastUpdated(created);
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.updateCategory(c);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	private void addCategoryWithImage(HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart) {
			System.out.println("Nothing to upload");
	    	return; 
	    }
	     
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    factory.setSizeThreshold(maxMemSize);
	    factory.setRepository(uploadDir);
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    upload.setSizeMax(maxFileSize);
	    
	    String catName = "";
	    String description = "";
	    String path = "";
	    String fileName = "";
	    
	      try
	      {
	    		List<FileItem>  items = upload.parseRequest(request);
	    		for(FileItem item:items)
	    		{ 
	    			if (item.isFormField()) {
	    				System.out.println("Getting value of form field");
	    				String name = item.getFieldName();//text1
	    			    String value = item.getString();
	    			    switch(name) {
	    			    case "name":
	    			    	catName = value;
	    			    	break;
	    			    case "description":
	    			    	description = value;
	    			    	break;
	    			    }
	    			}
	    			
	    			else {
	    	        	BeautyDAO beautyDAO = new BeautyDAOImpl();
	    	        	int lastImageId = beautyDAO.getLastImageId();

	    	            String fieldName = item.getFieldName();
	    	            fileName = String.valueOf(lastImageId) + item.getName();
	    	            String contentType = item.getContentType();
	    	            boolean isInMemory = item.isInMemory();
	    	            long sizeInBytes = item.getSize();
	    	            System.out.println(fileName);
	    	            
	    	            uploadDir = new File( myWebDir + "/uploads/categories");
	    	            
	    	            file = new File(uploadDir,fileName);
	    	            item.write(file);
	    	            
	    	            System.out.println("File created successfully");
	    	         }
	    		}
	    		
	    		Category c = new Category();
				c.setName(catName);
				c.setDescription(description);
				c.setPicture(fileName);
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				c.setAuthor(user.getId());
				Timestamp created = new Timestamp(System.currentTimeMillis());
				c.setLastUpdated(created);
				
				BeautyDAO beautyDAO = new BeautyDAOImpl();
				beautyDAO.addCategory(c);
	    		
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
	
	
	private void addPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			System.out.println("Trying to add the page");
			Page p = new Page();
			p.setTitle(request.getParameter("title"));
			p.setSlug(request.getParameter("slug"));
			p.setContent(request.getParameter("content"));
			p.setIsPublished(Integer.parseInt(request.getParameter("isPublished")));
			Timestamp created = new Timestamp(System.currentTimeMillis());
			p.setCreated(created);
			p.setHeading(request.getParameter("heading"));
			p.setSubheading(request.getParameter("subheading"));
			p.setTemplate(request.getParameter("template"));
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			p.setAuthor(user.getId());
			
			System.out.println("Page data: " + p.getTitle() + ", " + p.getSlug() + ", " + p.getContent() + " ," + p.getIsPublished() + ", " + p.getCreated());
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.addPage(p);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Page p = new Page();
			p.setId(Integer.parseInt(request.getParameter("id")));
			p.setTitle(request.getParameter("title"));
			p.setSlug(request.getParameter("slug"));
			p.setContent(request.getParameter("content"));
			p.setIsPublished(Integer.parseInt(request.getParameter("isPublished")));
			p.setTemplate(request.getParameter("template"));
			p.setHeading(request.getParameter("heading"));
			p.setSubheading(request.getParameter("subheading"));
			Timestamp created = new Timestamp(System.currentTimeMillis());
			p.setCreated(created);
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			p.setAuthor(user.getId());
			
			System.out.println("Trying to update the page, " + p.getSubheading());
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.updatePage(p);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void updateMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("Show menuorder");
			
			String type = request.getParameter("type");
			System.out.println("Print type " + type);
			
			String[] menuOrder = request.getParameterValues("pagesOrder[]");
			System.out.println("Print menuorder " + menuOrder);
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			
			for (int i = 0; i < menuOrder.length; i=i+2) {
				System.out.println("Page id: " + menuOrder[i] + ", order: " + menuOrder[i+1]);
				int pageId = Integer.parseInt(menuOrder[i]);
				int order = Integer.parseInt(menuOrder[i+1]);
				beautyDAO.updatePageOrder(pageId, order);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}		
	
	private void deletePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			int pageId = Integer.parseInt(request.getParameter("id"));
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.deletePage(pageId);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void getPageById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int pageId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Page page = beautyDAO.getPageById(pageId);
			request.setAttribute("page", page);

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}	
	
	private void getHomePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int pageId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Page page = beautyDAO.getPageById(pageId);
			request.setAttribute("page", page);

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}	
	
	
	private void updateService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Service s = new Service();
			s.setId(Integer.parseInt(request.getParameter("id")));
			s.setName(request.getParameter("name"));
			s.setDescription(request.getParameter("description"));
			String price = request.getParameter("price");
			int priceInt = Integer.parseInt(price);
			s.setPrice(priceInt);
			String catId = request.getParameter("categoryId");
			int categoryId = Integer.parseInt(catId);
			s.setCategoryId(categoryId);
			String timeString = request.getParameter("time");
			System.out.println("Nasz czas teraz: " + timeString);
			String betterString = timeString + ":00";
			System.out.println("Better string: " + betterString);
			Time mytime = Time.valueOf(betterString);
			System.out.println("Final czas: " + mytime);
			s.setTime(mytime);
			Timestamp created = new Timestamp(System.currentTimeMillis());
			s.setLastUpdated(created);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			s.setAuthor(user.getId());
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.updateService(s);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	
	private void addService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Service s = new Service();
			s.setName(request.getParameter("name"));
			s.setDescription(request.getParameter("description"));
			String price = request.getParameter("price");
			int priceInt = Integer.parseInt(price);
			s.setPrice(priceInt);
			String catId = request.getParameter("categoryId");
			int categoryId = Integer.parseInt(catId);
			s.setCategoryId(categoryId);
			String timeString = request.getParameter("time");
			String betterString = timeString + ":00";
			Time mytime = Time.valueOf(betterString);
			s.setTime(mytime);
			Timestamp created = new Timestamp(System.currentTimeMillis());
			s.setLastUpdated(created);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			s.setAuthor(user.getId());
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.addService(s);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void addEmployee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Employee e = new Employee();
			e.setFirstName(request.getParameter("firstName"));
			e.setLastName(request.getParameter("lastName"));
			e.setEmail(request.getParameter("email"));
			e.setTitle(request.getParameter("title"));
			e.setDescription(request.getParameter("description"));
			e.setProfilePicture(request.getParameter("profilePicture"));
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.addEmployee(e);
			
			System.out.println("New created employee ID is: " + e.getId());
			int employeeId = e.getId();
			
			String[] ids = request.getParameterValues("service");
		    for (String id:ids) {
		    	System.out.println("Selected ID is: " + id);
		    	int serviceId = Integer.parseInt(id);
				beautyDAO.addEmployeeService(employeeId, serviceId);
		    }

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void updateEmployee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Employee e = new Employee();
			e.setFirstName(request.getParameter("firstName"));
			e.setLastName(request.getParameter("lastName"));
			e.setEmail(request.getParameter("email"));
			e.setTitle(request.getParameter("title"));
			e.setDescription(request.getParameter("description"));
			e.setProfilePicture(request.getParameter("profilePicture"));
			e.setId(Integer.parseInt(request.getParameter("id")));
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.updateEmployee(e);
			
			int employeeId = e.getId();
			beautyDAO.deleteEmployeeServiceByEmployeeId(employeeId);
			
			String[] ids = request.getParameterValues("service");
		    for (String id:ids) {
		    	System.out.println("Selected ID is: " + id);
		    	int serviceId = Integer.parseInt(id);
				beautyDAO.addEmployeeService(employeeId, serviceId);
		    }
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
		
	
	private void updateCategoryWithImage(HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart) {
			System.out.println("Nothing to upload");
	    	return; 
	    }
	     
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    factory.setSizeThreshold(maxMemSize);
	    factory.setRepository(uploadDir);
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    upload.setSizeMax(maxFileSize);
	    
	    String categoryName = "";
	    String description = "";
	    String fileName = "";
	    String existingImagePath = null;
	    int id = 0;
	    
	      try
	      {
	    		List<FileItem>  items = upload.parseRequest(request);
	    		for(FileItem item:items)
	    		{ 
	    			if (item.isFormField()) {
	    				String name = item.getFieldName();
	    			    String value = item.getString();
	    			    switch(name) {
	    			    case "name":
	    			    	categoryName = value;
	    			    	break;
	    			    case "description":
	    			    	description = value;
	    			    	break;
	    			    case "id":
	    			    	System.out.println("The field is id, with value: " + value);
	    			    	id = Integer.parseInt(value);
	    			    	System.out.println("Now our id is: " + id);
	    			    	break;
	    			    case "existingImagePath":
	    			    	System.out.println("Taking existing image path, " + value);
	    			    	existingImagePath = value;
	    			    	break;
	    			    }
	    				
	    			}
	    			
	    			else {
	    	        	BeautyDAO beautyDAO = new BeautyDAOImpl();
	    	        	int lastImageId = beautyDAO.getLastImageId();

	    	            String fieldName = item.getFieldName();
	    	            fileName = String.valueOf(lastImageId) + item.getName();
	    	            String contentType = item.getContentType();
	    	            boolean isInMemory = item.isInMemory();
	    	            long sizeInBytes = item.getSize();
	    	            System.out.println(fileName);
	    	            
	    	            uploadDir = new File( myWebDir + "/uploads/categories");
	    	            
	    	            file = new File(uploadDir,fileName);
	    	            item.write(file);
	    	            
	    	            System.out.println("File created successfully");
	    	         }
	    		}
	    		
	    		Category c = new Category();
	    		c.setName(categoryName);
	    		c.setDescription(description);
	    		c.setId(id);
				
				String profilePicture;
				
				System.out.println("exidting path is: '" + existingImagePath + "'");
				if (!existingImagePath.equals("newFile")) {
					profilePicture = existingImagePath;
				}
				else {
					profilePicture = fileName;
				}
				
				System.out.println("PROFILE PICTURE IS: " + profilePicture);
				
				c.setPicture(profilePicture);
				Timestamp created = new Timestamp(System.currentTimeMillis());
				c.setLastUpdated(created);

				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				c.setAuthor(user.getId());
				
				System.out.println("Set profile picture: " + c.getPicture());

				System.out.println("existingImagePath is: " + existingImagePath + " filename is: " + fileName);
				
				BeautyDAO beautyDAO = new BeautyDAOImpl();
				System.out.println("Updating employee in db, " + c.getPicture());
				beautyDAO.updateCategory(c);

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
	
	
	private void deleteRecord(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			
			String tableName = request.getParameter("table");
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.deleteRecord(id, tableName);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	


}
